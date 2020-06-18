package web.urlSpider;

import org.apache.http.HttpEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class UrlBankBranchSubTask extends RecursiveTask< Map<String, BankBranchInfo>> {

    List<String> htmlUrlList;
    UrlProvinceCItyInfo urlProvinceCItyInfo;

    public UrlBankBranchSubTask(List<String> htmlUrlList, UrlProvinceCItyInfo urlProvinceCItyInfo) {
        this.htmlUrlList = htmlUrlList;
        this.urlProvinceCItyInfo = urlProvinceCItyInfo;
    }

    public UrlBankBranchSubTask(List<String> htmlUrlList) {
        this.htmlUrlList = htmlUrlList;
    }

    @Override
    protected  Map<String, BankBranchInfo> compute() {
        if (htmlUrlList.size() < 2) {
            try {
                return process(htmlUrlList);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            int size = htmlUrlList.size();
            UrlBankBranchSubTask j1 = new UrlBankBranchSubTask(htmlUrlList.subList(0, size / 2));
            UrlBankBranchSubTask j2 = new UrlBankBranchSubTask(htmlUrlList.subList(size / 2, htmlUrlList.size()));
            invokeAll(j1, j2);
            Map<String, BankBranchInfo> ll = j1.join();
            ll.putAll(j2.join());
            return ll;
        }
    }

    private Map<String, BankBranchInfo> process(List<String> htmlUrlList) throws IOException {
        BankBranchParseUtil b = new BankBranchParseUtil();
        //每一面数据对应的 联行号信息，key 为联行号4位，value为数据。
        Map<String, BankBranchInfo> map = new HashMap<>();


        String htmlUrl = htmlUrlList.get(0);
        System.out.println(htmlUrl);
        HttpEntity httpEntityByUrl = b.getHttpEntityByUrl(htmlUrl);
        List<BankBranchInfo> list1 = b.parseHtmlInfo(httpEntityByUrl, urlProvinceCItyInfo);

        for (BankBranchInfo br : list1) {
            if (!map.containsKey(br.getBranchNo().substring(3, 7))) {
                map.put(br.getBranchNo().substring(3, 7), br);
            }
        }
        return map;
    }
}
