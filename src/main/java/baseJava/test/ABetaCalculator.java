package baseJava.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A股Beta系数计算器
 * 使用真实A股数据计算个股相对于行业板块的Beta系数
 */
public class ABetaCalculator {

    // 股票基本信息类
    public static class StockInfo {
        private String symbol;      // 股票代码
        private String name;        // 股票名称
        private String industry;    // 所属行业

        public StockInfo(String symbol, String name, String industry) {
            this.symbol = symbol;
            this.name = name;
            this.industry = industry;
        }

        public String getSymbol() { return symbol; }
        public String getName() { return name; }
        public String getIndustry() { return industry; }
    }

    /**
     * K线数据类 - 存储单根K线的所有信息
     */
    public static class KLineData {
        private Date date;          // 交易日期
        private double open;        // 开盘价
        private double high;        // 最高价
        private double low;         // 最低价
        private double close;       // 收盘价
        private double volume;      // 成交量
        private double amount;      // 成交额

        public KLineData(Date date, double open, double high, double low, double close, double volume, double amount) {
            this.date = date;
            this.open = open;
            this.high = high;
            this.low = low;
            this.close = close;
            this.volume = volume;
            this.amount = amount;
        }

        // Getter方法
        public Date getDate() { return date; }
        public double getOpen() { return open; }
        public double getHigh() { return high; }
        public double getLow() { return low; }
        public double getClose() { return close; }
        public double getVolume() { return volume; }
        public double getAmount() { return amount; }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return String.format("Date: %s, Close: %.2f, Volume: %.0f",
                    sdf.format(date), close, volume);
        }
    }

    /**
     * 收益率数据类
     */
    public static class ReturnData {
        private Date date;
        private double returnRate;

        public ReturnData(Date date, double returnRate) {
            this.date = date;
            this.returnRate = returnRate;
        }

        public Date getDate() { return date; }
        public double getReturnRate() { return returnRate; }
    }

    /**
     * Beta计算结果类
     */
    public static class BetaResult {
        private double beta;
        private double alpha;
        private double rSquared;
        private double correlation;
        private int dataPoints;
        private String stockSymbol;
        private String stockName;
        private String sectorSymbol;
        private String sectorName;

        public BetaResult(double beta, double alpha, double rSquared,
                          double correlation, int dataPoints,
                          String stockSymbol, String stockName,
                          String sectorSymbol, String sectorName) {
            this.beta = beta;
            this.alpha = alpha;
            this.rSquared = rSquared;
            this.correlation = correlation;
            this.dataPoints = dataPoints;
            this.stockSymbol = stockSymbol;
            this.stockName = stockName;
            this.sectorSymbol = sectorSymbol;
            this.sectorName = sectorName;
        }

        // Getter方法
        public double getBeta() { return beta; }
        public double getAlpha() { return alpha; }
        public double getRSquared() { return rSquared; }
        public double getCorrelation() { return correlation; }
        public int getDataPoints() { return dataPoints; }
        public String getStockSymbol() { return stockSymbol; }
        public String getStockName() { return stockName; }
        public String getSectorSymbol() { return sectorSymbol; }
        public String getSectorName() { return sectorName; }

        @Override
        public String toString() {
            return String.format(
                    "Beta分析结果:\n" +
                            "股票: %s(%s) vs 板块: %s(%s)\n" +
                            "Beta系数: %.4f\n" +
                            "Alpha系数: %.4f%%\n" +
                            "R平方: %.4f\n" +
                            "相关系数: %.4f\n" +
                            "数据点数: %d\n" +
                            "风险特征: %s",
                    stockName, stockSymbol, sectorName, sectorSymbol,
                    beta, alpha * 100, rSquared, correlation, dataPoints, getRiskDescription()
            );
        }

        private String getRiskDescription() {
            if (beta > 1.5) return "高风险 - 波动性显著高于行业";
            else if (beta > 1.1) return "中高风险 - 波动性高于行业";
            else if (beta > 0.9) return "中性风险 - 波动性与行业相近";
            else if (beta > 0.5) return "中低风险 - 波动性低于行业";
            else return "低风险 - 波动性显著低于行业";
        }
    }

    /**
     * A股数据服务类 - 通过网络API获取真实数据
     */
    public static class AShareDataService {
        private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36";

        /**
         * 获取股票基本信息
         */
        public StockInfo getStockInfo(String symbol) {
            // 中际旭创的信息
            if ("300308".equals(symbol)) {
                return new StockInfo("300308", "中际旭创", "通信设备");
            }
            // 可以添加其他股票信息
            return new StockInfo(symbol, "未知股票", "未知行业");
        }

        /**
         * 获取个股历史K线数据（从公开数据源）
         * 使用新浪财经API或其他公开数据源
         */
        public List<KLineData> getStockHistory(String symbol, int days) {
            List<KLineData> result = new ArrayList<>();
            try {
                // 构建API URL - 使用新浪财经API
                String urlStr = String.format(
                        "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?" +
                                "symbol=sz%s&scale=240&ma=no&datalen=%d",
                        symbol, days
                );

                String response = sendHttpGetRequest(urlStr);
                result = parseSinaKLineData(response);

            } catch (Exception e) {
                System.err.println("获取股票数据失败: " + e.getMessage());
                // 如果网络请求失败，使用模拟数据作为fallback
                result = generateFallbackData(days, 50.0, 0.04);
            }

            // 按日期排序（确保时间顺序）
            result.sort(Comparator.comparing(KLineData::getDate));
            return result;
        }

        /**
         * 获取行业板块历史数据
         * 这里使用通信设备板块的代表性指数或构建板块指数
         */
        public List<KLineData> getSectorHistory(String industry, int days) {
            List<KLineData> result = new ArrayList<>();
            try {
                // 通信设备板块可以使用相关指数或构建板块平均
                // 这里使用中证通信设备指数作为近似（实际应用中可能需要更精确的板块数据）
                String urlStr = String.format(
                        "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?" +
                                "symbol=sz399908&scale=240&ma=no&datalen=%d",
                        days
                );

                String response = sendHttpGetRequest(urlStr);
                result = parseSinaKLineData(response);

            } catch (Exception e) {
                System.err.println("获取板块数据失败: " + e.getMessage());
                // 使用模拟数据作为fallback，但波动性较小
                result = generateFallbackData(days, 1000.0, 0.02);
            }

            // 按日期排序
            result.sort(Comparator.comparing(KLineData::getDate));
            return result;
        }

        /**
         * 发送HTTP GET请求
         */
        private String sendHttpGetRequest(String urlStr) throws Exception {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            } else {
                throw new Exception("HTTP请求失败，状态码: " + responseCode);
            }
        }

        /**
         * 解析新浪财经K线数据
         * 数据格式: [{day:"2023-01-01",open:"10.0",high:"10.5",low:"9.8",close:"10.2",volume:"1000000"},...]
         */
        private List<KLineData> parseSinaKLineData(String jsonData) {
            List<KLineData> result = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                // 简单的JSON解析（实际应用中建议使用JSON库）
                // 移除方括号
                String data = jsonData.substring(1, jsonData.length() - 1);
                // 分割各个数据点
                String[] items = data.split("\\},\\{");

                for (String item : items) {
                    // 清理字符串
                    item = item.replace("{", "").replace("}", "");
                    String[] fields = item.split(",");

                    String day = null;
                    double open = 0, high = 0, low = 0, close = 0, volume = 0;

                    for (String field : fields) {
                        String[] keyValue = field.split(":");
                        if (keyValue.length == 2) {
                            String key = keyValue[0].replace("\"", "").trim();
                            String value = keyValue[1].replace("\"", "").trim();

                            switch (key) {
                                case "day":
                                    day = value;
                                    break;
                                case "open":
                                    open = Double.parseDouble(value);
                                    break;
                                case "high":
                                    high = Double.parseDouble(value);
                                    break;
                                case "low":
                                    low = Double.parseDouble(value);
                                    break;
                                case "close":
                                    close = Double.parseDouble(value);
                                    break;
                                case "volume":
                                    volume = Double.parseDouble(value);
                                    break;
                            }
                        }
                    }

                    if (day != null) {
                        Date date = sdf.parse(day);
                        result.add(new KLineData(date, open, high, low, close, volume, volume * close));
                    }
                }

            } catch (Exception e) {
                System.err.println("解析K线数据失败: " + e.getMessage());
            }

            return result;
        }

        /**
         * 生成备用数据（当网络请求失败时使用）
         */
        private List<KLineData> generateFallbackData(int days, double basePrice, double volatility) {
            List<KLineData> dataList = new ArrayList<>();
            Calendar cal = Calendar.getInstance();
            Random random = new Random(42);

            for (int i = days - 1; i >= 0; i--) {
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, -i);
                Date date = cal.getTime();

                // 生成价格数据
                double change = (random.nextDouble() - 0.48) * volatility;
                double closePrice = basePrice * (1 + change);
                double openPrice = closePrice * (1 + (random.nextDouble() - 0.5) * 0.01);
                double highPrice = Math.max(openPrice, closePrice) * (1 + random.nextDouble() * 0.01);
                double lowPrice = Math.min(openPrice, closePrice) * (1 - random.nextDouble() * 0.01);
                double volume = 1000000 + random.nextDouble() * 500000;
                double amount = volume * closePrice;

                dataList.add(new KLineData(date, openPrice, highPrice, lowPrice, closePrice, volume, amount));
                basePrice = closePrice;
            }

            return dataList;
        }
    }

    /**
     * Beta计算核心类
     */
    public static class BetaCalculatorCore {

        /**
         * 计算收益率序列
         */
        public List<ReturnData> calculateReturns(List<KLineData> klineData) {
            List<ReturnData> returns = new ArrayList<>();

            for (int i = 1; i < klineData.size(); i++) {
                KLineData today = klineData.get(i);
                KLineData yesterday = klineData.get(i - 1);

                double returnRate = (today.getClose() - yesterday.getClose()) / yesterday.getClose();
                returns.add(new ReturnData(today.getDate(), returnRate));
            }

            return returns;
        }

        /**
         * 对齐收益率数据
         */
        public List<ReturnData>[] alignReturnData(List<ReturnData> stockReturns,
                                                  List<ReturnData> sectorReturns) {
            List<ReturnData> alignedStockReturns = new ArrayList<>();
            List<ReturnData> alignedSectorReturns = new ArrayList<>();

            Map<Date, Double> sectorReturnMap = new HashMap<>();
            for (ReturnData sectorReturn : sectorReturns) {
                sectorReturnMap.put(sectorReturn.getDate(), sectorReturn.getReturnRate());
            }

            for (ReturnData stockReturn : stockReturns) {
                Date date = stockReturn.getDate();
                if (sectorReturnMap.containsKey(date)) {
                    alignedStockReturns.add(stockReturn);
                    alignedSectorReturns.add(new ReturnData(date, sectorReturnMap.get(date)));
                }
            }

            @SuppressWarnings("unchecked")
            List<ReturnData>[] result = new List[] { alignedStockReturns, alignedSectorReturns };
            return result;
        }

        /**
         * 计算Beta系数
         */
        public BetaResult calculateBeta(List<ReturnData> stockReturns,
                                        List<ReturnData> sectorReturns,
                                        StockInfo stockInfo,
                                        String sectorSymbol,
                                        String sectorName) {
            // 对齐数据
            List<ReturnData>[] alignedReturns = alignReturnData(stockReturns, sectorReturns);
            List<ReturnData> alignedStockReturns = alignedReturns[0];
            List<ReturnData> alignedSectorReturns = alignedReturns[1];

            int n = alignedStockReturns.size();
            if (n < 2) {
                throw new IllegalArgumentException("有效数据点不足，无法计算Beta系数");
            }

            // 计算均值
            double stockMean = calculateMean(alignedStockReturns);
            double sectorMean = calculateMean(alignedSectorReturns);

            // 计算协方差和方差
            double covariance = calculateCovariance(alignedStockReturns, alignedSectorReturns,
                    stockMean, sectorMean);
            double sectorVariance = calculateVariance(alignedSectorReturns, sectorMean);

            // 计算Beta和Alpha
            double beta = covariance / sectorVariance;
            double alpha = stockMean - beta * sectorMean;

            // 计算相关系数和R平方
            double stockStdDev = calculateStandardDeviation(alignedStockReturns, stockMean);
            double sectorStdDev = calculateStandardDeviation(alignedSectorReturns, sectorMean);
            double correlation = covariance / (stockStdDev * sectorStdDev);
            double rSquared = correlation * correlation;

            return new BetaResult(beta, alpha, rSquared, correlation, n,
                    stockInfo.getSymbol(), stockInfo.getName(),
                    sectorSymbol, sectorName);
        }

        /**
         * 计算均值
         */
        private double calculateMean(List<ReturnData> returns) {
            double sum = 0.0;
            for (ReturnData returnData : returns) {
                sum += returnData.getReturnRate();
            }
            return sum / returns.size();
        }

        /**
         * 计算协方差
         */
        private double calculateCovariance(List<ReturnData> stockReturns,
                                           List<ReturnData> sectorReturns,
                                           double stockMean, double sectorMean) {
            double sum = 0.0;
            int n = stockReturns.size();

            for (int i = 0; i < n; i++) {
                double stockDiff = stockReturns.get(i).getReturnRate() - stockMean;
                double sectorDiff = sectorReturns.get(i).getReturnRate() - sectorMean;
                sum += stockDiff * sectorDiff;
            }

            return sum / (n - 1);
        }

        /**
         * 计算方差
         */
        private double calculateVariance(List<ReturnData> returns, double mean) {
            double sum = 0.0;
            for (ReturnData returnData : returns) {
                double diff = returnData.getReturnRate() - mean;
                sum += diff * diff;
            }
            return sum / (returns.size() - 1);
        }

        /**
         * 计算标准差
         */
        private double calculateStandardDeviation(List<ReturnData> returns, double mean) {
            return Math.sqrt(calculateVariance(returns, mean));
        }
    }

    /**
     * 主方法 - 分析中际旭创和通信设备板块
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== A股Beta系数分析 ===");
            System.out.println("股票: 中际旭创(300308) vs 通信设备板块\n");

            // 初始化服务
            AShareDataService dataService = new AShareDataService();
            BetaCalculatorCore calculator = new BetaCalculatorCore();

            // 获取股票信息
            StockInfo stockInfo = dataService.getStockInfo("300308");
            String sectorSymbol = "399908"; // 中证通信设备指数
            String sectorName = "通信设备板块";

            // 设置分析参数
            int analysisDays = 120; // 约6个月交易数据

            System.out.println("分析参数:");
            System.out.println("- 目标股票: " + stockInfo.getName() + "(" + stockInfo.getSymbol() + ")");
            System.out.println("- 所属行业: " + stockInfo.getIndustry());
            System.out.println("- 对比板块: " + sectorName + "(" + sectorSymbol + ")");
            System.out.println("- 分析周期: " + analysisDays + "个交易日\n");

            // 获取数据
            System.out.println("正在获取实时数据...");
            List<KLineData> stockData = dataService.getStockHistory("300308", analysisDays);
            List<KLineData> sectorData = dataService.getSectorHistory("通信设备", analysisDays);

            System.out.println("数据获取完成:");
            System.out.println("- 中际旭创数据: " + stockData.size() + "个K线");
            System.out.println("- 通信设备板块数据: " + sectorData.size() + "个K线");

            // 显示最新价格信息
            if (!stockData.isEmpty()) {
                KLineData latestStock = stockData.get(stockData.size() - 1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.printf("- 中际旭创最新收盘价(%.2f元 (日期: %s)\n",
                        latestStock.getClose(), sdf.format(latestStock.getDate()));
            }

            // 计算收益率
            System.out.println("\n正在计算收益率...");
            List<ReturnData> stockReturns = calculator.calculateReturns(stockData);
            List<ReturnData> sectorReturns = calculator.calculateReturns(sectorData);

            System.out.println("收益率计算完成:");
            System.out.println("- 中际旭创收益率序列: " + stockReturns.size() + "个数据点");
            System.out.println("- 通信设备板块收益率序列: " + sectorReturns.size() + "个数据点");

            // 计算Beta系数
            System.out.println("\n正在计算Beta系数...");
            BetaResult result = calculator.calculateBeta(stockReturns, sectorReturns,
                    stockInfo, sectorSymbol, sectorName);

            // 输出结果
            System.out.println("\n" + result.toString());

            // 显示详细分析
            displayDetailedAnalysis(result);

            // 显示投资建议
            displayInvestmentAdvice(result);

        } catch (Exception e) {
            System.err.println("分析过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 显示详细分析
     */
    private static void displayDetailedAnalysis(BetaResult result) {
        System.out.println("\n=== 详细分析 ===");

        // Beta分析
        System.out.println("1. Beta系数分析:");
        double beta = result.getBeta();
        if (beta > 1.2) {
            System.out.println("   - 该股票属于高Beta股票，对行业波动敏感");
            System.out.println("   - 在行业上涨时可能获得超额收益，下跌时跌幅可能更大");
        } else if (beta > 0.8) {
            System.out.println("   - 该股票Beta接近1，波动性与行业同步");
            System.out.println("   - 投资该股票相当于投资整个行业的平均表现");
        } else {
            System.out.println("   - 该股票属于低Beta股票，相对抗跌");
            System.out.println("   - 在市场下跌时可能表现相对较好");
        }

        // Alpha分析
        System.out.println("\n2. Alpha系数分析:");
        double alpha = result.getAlpha();
        if (alpha > 0.001) {
            System.out.println("   - 正Alpha表明该股票有超越行业的超额收益能力");
            System.out.println("   - 可能受益于公司特有的竞争优势或增长动力");
        } else if (alpha < -0.001) {
            System.out.println("   - 负Alpha表明该股票表现落后于行业平均水平");
            System.out.println("   - 可能需要关注公司基本面的变化");
        } else {
            System.out.println("   - Alpha接近零，收益与行业基准基本一致");
        }

        // 拟合优度分析
        System.out.println("\n3. 模型拟合度分析:");
        double rSquared = result.getRSquared();
        if (rSquared > 0.6) {
            System.out.println("   - 高R平方表明Beta值可靠性较高");
            System.out.println("   - 股票价格变动很大程度上可由行业因素解释");
        } else if (rSquared > 0.3) {
            System.out.println("   - 中等R平方，Beta值有一定参考价值");
            System.out.println("   - 除行业因素外，个股特质因素影响较大");
        } else {
            System.out.println("   - 低R平方，Beta值参考价值有限");
            System.out.println("   - 股票价格主要受个股特有因素驱动");
        }
    }

    /**
     * 显示投资建议
     */
    private static void displayInvestmentAdvice(BetaResult result) {
        System.out.println("\n=== 投资建议 ===");

        double beta = result.getBeta();
        double alpha = result.getAlpha();

        System.out.println("基于Beta分析的投资策略建议:");

        if (beta > 1.3 && alpha > 0.001) {
            System.out.println("1. 进攻型配置:");
            System.out.println("   - 该股票兼具高Beta和正Alpha，适合激进投资者");
            System.out.println("   - 在行业上升周期中可能获得显著超额收益");
            System.out.println("   - 建议在看好通信设备行业时重点配置");
        } else if (beta > 1.3 && alpha <= 0.001) {
            System.out.println("2. 行业贝塔交易:");
            System.out.println("   - 高Beta但无显著Alpha，收益主要来自行业波动");
            System.out.println("   - 适合进行行业轮动或波段操作");
            System.out.println("   - 建议结合行业景气度进行投资");
        } else if (beta < 0.8 && alpha > 0.001) {
            System.out.println("3. 防御型优选:");
            System.out.println("   - 低Beta但正Alpha，具备防御性和超额收益");
            System.out.println("   - 适合作为核心持仓，波动小且有超额收益");
            System.out.println("   - 建议长期持有，享受稳健回报");
        } else if (beta < 0.8 && alpha <= 0.001) {
            System.out.println("4. 稳定配置:");
            System.out.println("   - 低Beta且无显著Alpha，波动小但收益一般");
            System.out.println("   - 适合风险厌恶型投资者或作为组合稳定器");
            System.out.println("   - 建议在市场不确定性高时配置");
        } else {
            System.out.println("5. 均衡配置:");
            System.out.println("   - Beta接近1，与行业同步波动");
            System.out.println("   - 适合作为行业代表性配置");
            System.out.println("   - 建议根据对行业整体判断进行投资");
        }

        System.out.println("\n风险提示:");
        System.out.println("- Beta系数基于历史数据计算，未来可能发生变化");
        System.out.println("- 投资决策应结合公司基本面、行业前景等多因素");
        System.out.println("- 市场有风险，投资需谨慎");
    }
}