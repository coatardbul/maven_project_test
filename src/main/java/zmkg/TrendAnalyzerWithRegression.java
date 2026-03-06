package zmkg;

public class TrendAnalyzerWithRegression {

    public static void main(String[] args) {
        long[] data = {
                3143322, 6249198, 2330991, 2153430, 34291702, 10398600, 23739816,
                127386440, 19613312, 26092390, 57600198, 34615187, 51571728,
                44182530, 30534176, 126895095
        };

        analyzeTrendWithRegression(data);
    }

    public static void analyzeTrendWithRegression(long[] data) {
        int n = data.length;
        double[] x = new double[n];
        double[] y = new double[n];

        // 准备 x 和 y 数组
        for (int i = 0; i < n; i++) {
            x[i] = i;         // x 是索引
            y[i] = data[i];   // y 是数据值
        }

        // 进行线性回归
        double[] regressionParams = linearRegression(x, y);
        double slope = regressionParams[0];      // 斜率
        double intercept = regressionParams[1];  // 截距

        System.out.println("趋势线方程: y = " + slope + " * x + " + intercept);

        // 计算趋势线的值
        double[] trendLine = new double[n];
        for (int i = 0; i < n; i++) {
            trendLine[i] = slope * x[i] + intercept;
        }

        // 检查是否有数据点击穿趋势线
        boolean isTrendBroken = false;
        for (int i = 0; i < n; i++) {
            if (data[i] < trendLine[i]) {
                System.out.println("趋势被击穿：索引 " + i + " (数据值: " + data[i] +
                        ", 趋势线值: " + trendLine[i] + ")");
                isTrendBroken = true;
            }
        }

        if (!isTrendBroken) {
            System.out.println("趋势完好，没有击穿。");
        }

        // 输出趋势线值（用于参考）
        System.out.println("趋势线值：");
        for (int i = 0; i < n; i++) {
            System.out.println("索引 " + i + ": " + trendLine[i]);
        }
    }

    // 线性回归方法，用于计算斜率和截距
    public static double[] linearRegression(double[] x, double[] y) {
        int n = x.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumX2 += x[i] * x[i];
        }

        // 计算斜率 (m)
        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);

        // 计算截距 (b)
        double intercept = (sumY - slope * sumX) / n;

        return new double[]{slope, intercept};
    }
}
