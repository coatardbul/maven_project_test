package zmkg;

import smile.classification.RandomForest;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 最小可跑的训练/预测框架示例
 * - 自定义 FeatureExtractor 接口
 * - 用 RandomForest.fit 训练（内存中，不依赖 CSV）
 * - 提供保存/加载模型的方法
 *
 * 注意：这是示例骨架，请用你的真实 FeatureExtractor 替换 ExampleExtractor。
 */
public class TickModelTrainer {

    // 让用户实现：把一天的多笔Tick转换成一个特征向量
    public interface FeatureExtractor {
        double[] extractFeatures(List<TickInfo> ticks);
    }

    /**
     * 训练模型，返回 RandomForest
     *
     * @param dayToTicks Map<dayId, List<TickInfo>>
     * @param labels Map<dayId, Integer>  标签 例如 吸筹=1, 洗盘=0
     * @param extractor 用户实现的特征抽取器
     * @param nTrees 随机森林树数
     */
    public static RandomForest train(Map<String, List<TickInfo>> dayToTicks,
                                     Map<String, Integer> labels,
                                     FeatureExtractor extractor,
                                     int nTrees) {
        List<double[]> featureList = new ArrayList<>();
        List<Integer> labelList = new ArrayList<>();

        for (Map.Entry<String, Integer> e : labels.entrySet()) {
            String dayId = e.getKey();
            Integer lbl = e.getValue();
            List<TickInfo> ticks = dayToTicks.get(dayId);
            if (ticks == null || ticks.isEmpty()) {
                System.out.println("跳过 dayId (无数据): " + dayId);
                continue;
            }

            // 保证按时间排序（如果你的 time 是 HH:mm:ss 字符串，这里按字典序排序可行）
            ticks.sort(Comparator.comparing(TickInfo::getTime));

            double[] feat = extractor.extractFeatures(ticks);
            if (feat == null || feat.length == 0) {
                System.out.println("跳过 dayId (无效特征): " + dayId);
                continue;
            }

            featureList.add(feat);
            labelList.add(lbl);
        }

        if (featureList.isEmpty()) {
            throw new IllegalArgumentException("没有有效训练样本，请检查 dayToTicks 与 labels");
        }

        double[][] X = featureList.toArray(new double[0][]);
        int[] y = labelList.stream().mapToInt(Integer::intValue).toArray();

        // 训练 RandomForest（使用 smile.api）

        int correct = 0;

        return null;
    }

    /**
     * 使用模型对一天数据做预测（返回标签和特征向量）
     */
    public static PredictResult predict(RandomForest model, List<TickInfo> ticks, FeatureExtractor extractor) {
        if (ticks == null || ticks.isEmpty()) throw new IllegalArgumentException("ticks 为空");
        ticks.sort(Comparator.comparing(TickInfo::getTime));
        double[] feat = extractor.extractFeatures(ticks);
        if (feat == null || feat.length == 0) throw new IllegalStateException("特征为空");

        // 修正：包装为二维数组
        int[] pred = null;
        return new PredictResult(pred[0], feat);
    }


    public static class PredictResult {
        public final int label;
        public final double[] features;
        public PredictResult(int label, double[] features) {
            this.label = label;
            this.features = features;
        }
    }

    // 模型序列化保存
    public static void saveModel(RandomForest model, String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(model);
        }
    }

    // 模型加载
    public static RandomForest loadModel(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (RandomForest) ois.readObject();
        }
    }

    // ---------------------------
    // 示例 FeatureExtractor：替换为你的自定义特征
    // ---------------------------
    public static class ExampleExtractor implements FeatureExtractor {
        @Override
        public double[] extractFeatures(List<TickInfo> ticks) {
            if (ticks == null || ticks.isEmpty()) return new double[0];

            int n = ticks.size();
            double[] prices = ticks.stream().mapToDouble(t -> t.getPrice().doubleValue()).toArray();
            double avgPrice = Arrays.stream(prices).average().orElse(0.0);
            double std = Math.sqrt(Arrays.stream(prices).map(p -> Math.pow(p - avgPrice, 2)).average().orElse(0.0));

            long upCount = ticks.stream().filter(t -> "UP".equals(t.getBuySellFlag())).count();
            double upRatio = (double) upCount / n;

            double totalVol = ticks.stream().mapToDouble(t -> t.getVol().doubleValue()).sum();
            double upVol = ticks.stream()
                    .filter(t -> "UP".equals(t.getBuySellFlag()))
                    .mapToDouble(t -> t.getVol().doubleValue()).sum();
            double upVolRatio = totalVol == 0 ? 0 : upVol / totalVol;

            int contMax = 0, cur = 0;
            for (TickInfo t : ticks) {
                if ("UP".equals(t.getBuySellFlag())) {
                    cur++;
                    contMax = Math.max(contMax, cur);
                } else cur = 0;
            }

            int tail = Math.min(5, n);
            double tailVol = ticks.subList(n - tail, n).stream().mapToDouble(t -> t.getVol().doubleValue()).sum();
            double avgVol = n == 0 ? 0 : totalVol / n;
            double tailVolRatio = avgVol == 0 ? 0 : tailVol / (avgVol * tail);

            return new double[]{
                    avgPrice,
                    std,
                    upRatio,
                    upVolRatio,
                    contMax,
                    tailVolRatio
            };
        }
    }

    // ---------------------------
    // main 用于演示（用伪造数据训练并预测）
    // ---------------------------
    public static void main(String[] args) throws Exception {
        // 生成伪造的按天数据（请替换为你从接口获取并 convertTickInfo(responseStr) 的数据）
        Map<String, List<TickInfo>> dayToTicks = new HashMap<>();
        dayToTicks.put("2025-10-28_600519", generateMockTicks(100, 10.0));
        dayToTicks.put("2025-10-29_600519", generateMockTicks(120, 10.2));
        dayToTicks.put("2025-10-30_600519", generateMockTicks(90, 9.8));

        // 你人工给定的标签（训练样本）
        Map<String, Integer> labels = new HashMap<>();
        labels.put("2025-10-28_600519", 1); // 吸筹
        labels.put("2025-10-29_600519", 0); // 洗盘
        labels.put("2025-10-30_600519", 1); // 吸筹

        FeatureExtractor extractor = new ExampleExtractor();

        RandomForest model = train(dayToTicks, labels, extractor, 100);

        // 测试预测：
        List<TickInfo> newDay = generateMockTicks(110, 10.05);
        PredictResult res = predict(model, newDay, extractor);
        System.out.println("预测标签: " + res.label + ", 特征: " + Arrays.toString(res.features));

        // 保存与加载示例
        String modelPath = "tick_rf.model";
        saveModel(model, modelPath);
        RandomForest loaded = loadModel(modelPath);
        PredictResult res2 = predict(loaded, newDay, extractor);
        System.out.println("加载后预测: " + res2.label);
    }

    // 生成模拟 Tick 数据（仅演示）
    private static List<TickInfo> generateMockTicks(int n, double basePrice) {
        List<TickInfo> list = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            TickInfo t = new TickInfo();
            int hh = 9 + (i / 3600);
            int mm = (i / 60) % 60;
            int ss = i % 60;
            t.setTime(String.format("%02d:%02d:%02d", hh, mm, ss));
            double p = basePrice + (rnd.nextDouble() - 0.5) * 0.05;
            t.setPrice(BigDecimal.valueOf(Math.round(p * 100.0) / 100.0));
            double vol = 500 + rnd.nextInt(4500);
            t.setVol(BigDecimal.valueOf(vol));
            t.setBuySellFlag(rnd.nextDouble() > 0.5 ? "UP" : "DOWN");
            list.add(t);
        }
        return list;
    }
}
