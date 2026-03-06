package stock;

import ai.z.openapi.ZhipuAiClient;
import ai.z.openapi.service.model.ChatCompletionCreateParams;
import ai.z.openapi.service.model.ChatCompletionResponse;
import ai.z.openapi.service.model.ChatMessage;
import ai.z.openapi.service.model.ChatMessageRole;
import ai.z.openapi.service.model.ChatThinking;
import java.util.Arrays;

public class BasicChat {
    public static void main(String[] args) {
        // 初始化客户端
        ZhipuAiClient client = ZhipuAiClient.builder().ofZHIPU()
                .apiKey("a7942a5e94024e85b06a3301f866c247.8fSS0bMz8rHj184K")
                .build();

        // 创建聊天完成请求
        ChatCompletionCreateParams request = ChatCompletionCreateParams.builder()
                .model("glm-4.7-flash")
                .messages(Arrays.asList(
                        ChatMessage.builder()
                                .role(ChatMessageRole.SYSTEM.value())
                                .content("你是一位专业的股票分析师，请根据以下信息判断用户提供的题材是否与该股票匹配：")
                                .build(),
                        ChatMessage.builder()
                                .role(ChatMessageRole.USER.value())
                                .content("【股票基本信息】\n" +
                                        "蓝色光标\n" +
                                        "\n" +
                                        "【公司介绍】\n" +
                                        "蓝色光标是一家在大数据和社交网络时代为企业智慧经营全面赋能的营销科技公司。蓝色光标及其旗下子公司的业务板块包括：全案推广服务（数字营销、公共关系、活动管理等）、全案广告代理（数字广告投放、中国企业出海广告投放代理等）以及元宇宙相关业务（虚拟人、虚拟物、虚拟空间以及xR影棚），服务内容涵盖营销传播整个产业链，以及基于营销科技的智慧经营服务，服务地域基本覆盖全球主要市场。\n" +
                                        "\n" +
                                        "\n" +
                                        "\n" +
                                        "【用户提供的待验证题材列表】\n" +
                                        "AI营销\n" +
                                        "人工智能大模型\n" +
                                        "小红书概念股\n" +
                                        "传媒\n" +
                                        "Sora AI视频\n" +
                                        "ChatGPT\n" +
                                        "业绩增长\n" +
                                        "\n" +
                                        "【热点相关介绍】\n" +
                                        "1、网传公司通过真格基金间接参股monica（未证实）。蓝色光标与火山引擎达成深度合作，双方将基于火山方舟、豆包·视频生成模型、扣子专业版智能体开发平台，在视频生成、视频服务解决方案以及营销行业智能体应用等领域展开深入合作。 2、2024年上半年，公司在与包括微软，谷歌，亚马逊，字节跳动，百度，智谱，月之暗面等国内外主流Maas及云厂商合作的基础上，构建了AI时代营销应用的数据底座，发展出包括小红书，b站，抖音洞察等国内业务AI营销工具矩阵，Meta,Instagram, YouTube, TikTok for Business分析助手，广告投放分析助手等全球化业务AI营销工具矩阵。 3、公司是Meta、Google、TikTok for Business、X等平台国内官方授权代理商，为中国出海企业包括跨境电商、本土品牌、移动游戏、应用工具等行业客户开展出海广告服务。 4、公司已与微软广告达成核心代理的战略合作，已获得微软云官方AI调用和训练许可。 5、蓝色宇宙专注于元宇宙营销场景解决方案，布局虚拟人、虚拟物、虚拟空间三个业务板块。\n" +
                                        "\n" +
                                        "1、公司深度合作火山引擎，双方将基于火山方舟、豆包视频生成模型、扣子专业版智能体开发平台，在视频生成、视频服务解决方案以及营销行业智能体应用等领域展开深入合作。 2、真格基金为Manus种子轮融资方，蓝色光标参与投资了天津真格天弘资产管理合伙企业。 3、公司在与包括微软，谷歌，字节跳动，MiniMax，智谱，月之暗面等国内外主流Maas及云厂商合作的基础上，构建了AI时代营销应用的数据底座，广告投放分析助手等全球化业务AI营销工具矩阵。 4、2025年12月30日晚公告，已向港交所递交了上市申请，同时更新了发行申请及部分公司信息和财务数据等。 5、出海广告投放业务占比约83%，是Meta、Google等平台国内官方授权代理商，为中国出海企业开展出海广告服务。\n" +
                                        "\n" +
                                        "AI营销+火山引擎合作+MiniMax/Manus相关+拟在港上市 1、公司深度合作火山引擎，双方将基于火山方舟、豆包视频生成模型、扣子专业版智能体开发平台，在视频生成、视频服务解决方案以及营销行业智能体应用等领域展开深入合作。 2、真格基金为Manus种子轮融资方，蓝色光标参与投资了天津真格天弘资产管理合伙企业。 3、公司在与包括微软，谷歌，字节跳动，MiniMax，智谱，月之暗面等国内外主流Maas及云厂商合作的基础上，构建了AI时代营销应用的数据底座，广告投放分析助手等全球化业务AI营销工具矩阵。 4、2025年12月30日晚公告，已向港交所递交了上市申请，同时更新了发行申请及部分公司信息和财务数据等。 5、出海广告投放业务占比约83%，是Meta、Google等平台国内官方授权代理商，为中国出海企业开展出海广告服务。")
                                .build(),
                        ChatMessage.builder()
                                .role(ChatMessageRole.ASSISTANT.value())
                                .content("请按以下格式输出：\n" +
                                        "1. 符合的题材：[题材名] - 匹配理由 - 置信度(0-100)\n" +
                                        "2. 不符合的题材：[题材名] - 剔除理由 - 置信度(0-100)\n" +
                                        "3. 建议补充的题材：[如有]")
                                .build()
                ))
                .thinking(ChatThinking.builder().type("enabled").build())
                .maxTokens(65536)
                .temperature(1.0f)
                .build();

        // 发送请求
        ChatCompletionResponse response = client.chat().createChatCompletion(request);

        // 获取回复
        if (response.isSuccess()) {
            Object reply = response.getData().getChoices().get(0).getMessage();
            System.out.println("AI 回复: " + reply);
        } else {
            System.err.println("错误: " + response.getMsg());
        }
    }
}