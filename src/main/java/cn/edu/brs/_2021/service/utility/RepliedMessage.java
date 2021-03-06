package cn.edu.brs._2021.service.utility;

public abstract class RepliedMessage {
    public static String getRepliedMessage(String questionCode){
        return switch (questionCode) {
            case "0-0"                  ->      "你终于来啦\uD83D\uDE18，这里是王府学校\uD83C\uDFEB2021届毕业生公众号。\n" +
                                                "\n" +
                                                "我是攻城狮小狮\uD83E\uDD81。在接下来的一个学期\uD83C\uDF1C里，小狮\uD83E\uDD81会负责传达一系列关于毕业典礼\uD83C\uDF91的信息，你也可以在这里查询到你的日程\uD83D\uDDD3️和活动\uD83E\uDD3C\n" +
                                                "\n" +
                                                "不过在此之前，你可能要先将你和你的个人信息\uD83D\uDCDB绑定在一起，小狮\uD83E\uDD81可以知道你的名字么\uD83D\uDC40";

            case "0-0-nameNotFound"     ->      "小狮\uD83E\uDD81找不到您输入的 \"#{这个名字}\" 呢\uD83D\uDE11，请输入你的真实名字，或直接联系小狮处理\uD83E\uDD14\n" +
                                                "\n" +
                                                "小狮的邮箱\uD83D\uDCE8: brs2021Graduates@outlook.com\n" +
                                                "小狮的微信\uD83D\uDCF2: brs2021Graduates";

            case "0-0-alreadyBind"      ->      "小狮\uD83E\uDD81看到姓名 \"#{这个名字} \" 已经绑定了微信号。\uD83D\uDE15。\n" +
                                                "\n" +
                                                "如果不是你绑定的，请联系小狮处理呢。\uD83E\uDD14\n" +
                                                "\n" +
                                                "小狮的邮箱\uD83D\uDCE8: brs2021Graduates@outlook.com\n" +
                                                "小狮的微信\uD83D\uDCF2: brs2021Graduates";

            case "0-0-redisExpire"      ->      "距离你输入名字的时间有点长\uD83E\uDD14小狮找不到关于你的记录了\uD83D\uDE15可以麻烦小主再输入一下你的姓名么\uD83D\uDE09";

            case "0-1"                  ->      "小狮\uD83E\uDD81️需要通过学号验证一下是不是坏人\uD83E\uDDDE想要偷偷绑定小主的身份\uD83C\uDD94。\n" +
                                                "\n" +
                                                "可以拥有小主的学号么\uD83D\uDC99";

            case "0-1-invalidInput"     ->      "学号格式似乎不对\uD83D\uDE15，请小主输入和希悦系统一致的学号呢\uD83D\uDE36";

            case "0-1-validationFailed" ->      "这边没有找到您的学号[苦涩]，请小主输入和希悦系统一致的学号呢\uD83D\uDE36";

            case "0-2"                  ->      "小主是仙女\uD83E\uDDDA\u200D♀还是仙男\uD83E\uDDDA\u200D♂？\n" +
                                                "\n" +
                                                "仙女\uD83D\uDC71\u200D♀扣0 仙男\uD83D\uDC71\u200D♂扣1";

            case "0-2-invalidInput"     ->      "小狮\uD83E\uDD81️提醒：请输入0 / 1两个数字哦。\n" +
                                                "如果您有其它的性别，请联系小狮处理呢。\uD83E\uDD14\n" +
                                                "\n" +
                                                "小狮的邮箱\uD83D\uDCE8: brs2021Graduates@outlook.com\n" +
                                                "小狮的微信\uD83D\uDCF2: brs2021Graduates";

            case "0-3"                  ->      "小主选一个取个自己喜欢的昵称吧[让我看看]\n" +
                                                "\n" +
                                                "注：为了让小狮\uD83E\uDD81️的狮毛不要过早的凋谢\uD83E\uDD40，昵称必须是由 4-12位中文，英文，字母组成的哦。";

            case "0-3-invalidInput"     ->      "这个昵称不能通过验证呢小主\uD83E\uDD15请再尝试一次\uD83D\uDE33";

            case "0-4"                  ->      "请小主设置一个密码\uD83D\uDE08，密码用于网页版活动信息查询\n" +
                                                "\n" +
                                                "注：密码需要以字母开头，6-18位，仅包括字母，数字，下划线";

            case "0-4-invalidInput"     ->      "这个密码不能通过验证呢小主\uD83E\uDD15请再尝试一次\uD83D\uDE33";


            case "9"                    ->      "欢迎小主，小狮有什么可以帮到你的呢\uD83D\uDC40\n" +
                                                "[1] \uD83E\uDD3C 活动相关\n" +
                                                "[2] \uD83E\uDD47 积分相关\n" +
                                                "[3] \uD83D\uDCE8 意见反馈";

            case "menu-invalidInput"    ->      "请小主输入菜单前的数字以进行下一步呢:/";

            case "1"                    ->      "[ \uD83E\uDD3C 活动相关 ] \n" +
                                                "[1]  \uD83E\uDD3C 活动查询\n" +
                                                "[2]  \uD83E\uDD42 组队查询\n" +
                                                "[9]  \uD83D\uDD19 返回主菜单";


            case "1-1"                  ->      "[ \uD83E\uDD3C 活动查询 ]\n" +
                                                "========== 普通活动 ==========\n" +
                                                "#{普通活动信息}\n" +
                                                "========== 排名比赛 ==========\n" +
                                                "#{排名比赛信息}\n" +
                                                "========== 单人比赛 ==========\n" +
                                                "#{单人比赛信息}\n" +
                                                "========== 团体比赛 ==========\n" +
                                                "#{团体比赛信息}";

            case "1-2"                  ->      "[ \uD83E\uDD42 组队查询 ]\n" +
                                                "========== 组队信息 ==========\n" +
                                                "#{组队信息}";

            case "2"                    ->      "[ \uD83E\uDD47 积分相关 ] \n" +
                                                "[1]  \uD83E\uDD14 我的积分情况\n" +
                                                "[2]  \uD83E\uDD3C 个人积分排行\n" +
                                                "[3]  \uD83E\uDD42 团体积分排行\n" +
                                                "[9] \uD83D\uDD19  返回上级";

            case "2-1"                  ->      "[ \uD83E\uDD14 我的积分情况 ] \n" +
                                                "========== 个人积分 ==========\n" +
                                                "#{个人积分}\n" +
                                                "========== 所属团队 ==========\n" +
                                                "#{团队积分}";

            case "2-2"                  ->      "[ \uD83E\uDD3C 个人积分排行 ] \n" +
                                                "========== 个人积分排行榜 ==========\n" +
                                                "#{排行榜}";

            case "2-3"                  ->      "[ \uD83E\uDD42 团体积分排行 ] \n" +
                                                "========== 团体积分排行榜 ==========\n" +
                                                "#{排行榜}";

            case "3"                    ->      "[ \uD83D\uDCE8 意见反馈 ] \n" +
                                                "很抱歉对小主造成了困扰\uD83D\uDE16，请将您的意见和建议直接输入至聊天框内并发送。";


            case "3-1"                  ->      "感谢小主的意见和建议，我们会尽快处理并向您反馈结果。";




            //Impossible
            default -> "";

            
        };
    }
}
