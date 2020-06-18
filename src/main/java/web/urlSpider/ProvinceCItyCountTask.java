package web.urlSpider;

import org.apache.http.HttpEntity;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ProvinceCItyCountTask extends RecursiveTask<List<ProvinceCItyInfo>> {
    List<String> urlList;

    public ProvinceCItyCountTask(List<String> urlList) {
        this.urlList = urlList;
    }

    @Override
    protected List<ProvinceCItyInfo> compute() {
        if (urlList.size() < 2) {
            try {
                return process(urlList);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            int size = urlList.size();
            ProvinceCItyCountTask j1 = new ProvinceCItyCountTask(urlList.subList(0, size / 2));
            ProvinceCItyCountTask j2 = new ProvinceCItyCountTask(urlList.subList(size / 2, urlList.size()));
            invokeAll(j1, j2);
            List<ProvinceCItyInfo> l = j1.join();
            l.addAll(j2.join());
            return l;
        }
    }

    public List<ProvinceCItyInfo> process(List<String> urlList) throws IOException {
        BankBranchParseUtil b = new BankBranchParseUtil();
        if (urlList == null || urlList.size() == 0) {
            return null;
        }
        // Thread.sleep((long) (Math.random() * (100)));
        HttpEntity httpEntityByUrl = b.getHttpEntityByUrl(urlList.get(0));
        //返回单个省对应的多个市的集合
        List<ProvinceCItyInfo> provinceCItyInfos = b.parseProviceCItyInfo(httpEntityByUrl);
        return provinceCItyInfos;

    }
}
