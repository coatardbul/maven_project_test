package web.url;

import org.apache.http.HttpEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class UrlBankBranchTask extends RecursiveTask<List<BankBranchCollectionInfo>> {

    List<UrlProvinceCItyInfo> urlProvinceCItyInfoList;

    public UrlBankBranchTask(List<UrlProvinceCItyInfo> urlProvinceCItyInfoList) {
        this.urlProvinceCItyInfoList = urlProvinceCItyInfoList;
    }


    @Override
    protected List<BankBranchCollectionInfo> compute() {
        List<BankBranchCollectionInfo> ll=new ArrayList<>();
        if (urlProvinceCItyInfoList.size() < 2) {
            try {
                ll.add(process(urlProvinceCItyInfoList));
                return ll;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            int size = urlProvinceCItyInfoList.size();
            UrlBankBranchTask j1 = new UrlBankBranchTask(urlProvinceCItyInfoList.subList(0, size / 2));
            UrlBankBranchTask j2 = new UrlBankBranchTask(urlProvinceCItyInfoList.subList(size / 2, urlProvinceCItyInfoList.size()));
            invokeAll(j1, j2);
            ll.addAll(j1.join());
            ll.addAll(j2.join());
            return ll;
        }
    }

    private BankBranchCollectionInfo process(List<UrlProvinceCItyInfo> urlProvinceCItyInfoList) throws IOException {
        BankBranchParseUtil b = new BankBranchParseUtil();

        //一个市对应的联行号信息
        List<BankBranchInfo> bankBranchInfos = new ArrayList<>();

        Map<String, BankBranchInfo> map = new HashMap<>();
        BankBranchCollectionInfo bankBranchCollectionInfo = new BankBranchCollectionInfo();
        for (int j = 1; j < BankBranchParseUtil.PAGE; j++) {
            //   Thread.sleep((long) (Math.random() * (100)));
            String htmlUrl = b.getHtmlUrl(urlProvinceCItyInfoList.get(0).getUrl(), j);
            System.out.println(htmlUrl);
            HttpEntity httpEntityByUrl = b.getHttpEntityByUrl(htmlUrl);
            List<BankBranchInfo> list1 = b.parseHtmlInfo(httpEntityByUrl, urlProvinceCItyInfoList.get(0));

            for (BankBranchInfo br : list1) {
                map.put(br.getBranchNo().substring(3, 7), br);
            }
            if (list1.size() > 0) {
                bankBranchInfos.addAll(list1);

            }
        }
        bankBranchCollectionInfo.setId(urlProvinceCItyInfoList.get(0).getSid());
        bankBranchCollectionInfo.setMap(map);
        System.out.println(map);
        return bankBranchCollectionInfo;

    }
}
