package network.urlSpider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class UrlBankBranchTask extends RecursiveTask<List<BankBranchCollectionInfo>> {

    List<UrlProvinceCItyInfo> urlProvinceCItyInfoList;

    public UrlBankBranchTask(List<UrlProvinceCItyInfo> urlProvinceCItyInfoList) {
        this.urlProvinceCItyInfoList = urlProvinceCItyInfoList;
    }


    @Override
    protected List<BankBranchCollectionInfo> compute() {
        if (urlProvinceCItyInfoList.size() < 2) {

                List<BankBranchCollectionInfo> ll = new ArrayList<>();
                ll.add(process(urlProvinceCItyInfoList));
                return ll;

        } else {
            int size = urlProvinceCItyInfoList.size();
            UrlBankBranchTask j1 = new UrlBankBranchTask(urlProvinceCItyInfoList.subList(0, size / 2));
            UrlBankBranchTask j2 = new UrlBankBranchTask(urlProvinceCItyInfoList.subList(size / 2, urlProvinceCItyInfoList.size()));
            invokeAll(j1, j2);
            List<BankBranchCollectionInfo> ll = j1.join();
            ll.addAll(j2.join());
            return ll;
        }
    }

    private BankBranchCollectionInfo process(List<UrlProvinceCItyInfo> urlProvinceCItyInfoList)   {
        BankBranchParseUtil b = new BankBranchParseUtil();

        BankBranchCollectionInfo bankBranchCollectionInfo = new BankBranchCollectionInfo();



        List<String> htmlUrlList = new ArrayList<>();
        for (int j = 1; j < BankBranchParseUtil.PAGE; j++) {
            htmlUrlList.add(b.getHtmlUrl(urlProvinceCItyInfoList.get(0).getUrl(), j));
        }
        ForkJoinPool f = new ForkJoinPool(BankBranchParseUtil.PAGE);
        UrlBankBranchSubTask urlBankBranchSubTask = new UrlBankBranchSubTask(htmlUrlList,urlProvinceCItyInfoList.get(0));
        Map<String, BankBranchInfo> map = null;
        try {
            map = f.submit(urlBankBranchSubTask).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        bankBranchCollectionInfo.setId(urlProvinceCItyInfoList.get(0).getSid());
        bankBranchCollectionInfo.setMap(map);
        System.out.println(map);
        f.shutdown();
        return bankBranchCollectionInfo;

    }
}
