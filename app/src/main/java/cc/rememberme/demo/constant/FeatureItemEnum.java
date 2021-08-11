package cc.rememberme.demo.constant;


/**
 * @author : guojialin
 * @date : 2021/8/8 09:58
 */
public enum FeatureItemEnum {

    LAYOUT(1, "Layout Samples"),
    DATA_BINDING(2, "Data Binding"),
    TASK_AFFINITY(3, "Task Affinity"),
    VIEW_COMPONENTS(4, "基础控件"),
    SERVICE(5, "Service"),
    BROADCAST_RECEIVER(6, "Broadcast Receiver"),
    CONTENT_PROVIDER(7, "Content Provider"),
    INTENT(8, "Intent"),
    APPLICATION(9, "Application"),
    RXJAVA(10, "RxJava"),
    CUSTOMIZED_VIEW(11, "自定义控件 研究"),
    TOUCH_EVENT(12, "触摸事件"),
    DRAW(13, "View绘制事件"),
    ANIM(14, "动画事件"),
    LOGGER(15, "Logger"),
    SCREEN(16, "Screen Size Study"),
    MULTI_LANGUAGE(17, "多语言"),
    API_LEVELS(18, "API Levels"),
    HANDLER(19, "Handler"),
    SYSTEM_API(20, "系统API"),
    ASYNC_TASKS(21, "异步技术"),
    NETWORK(22, "网络请求"),
    RICH_MEDIA(23, "多媒体"),
    FILE_DOWNLOADER(24, "文件下载器"),
    BUILD(25, "构建系统"),
    ANDROID_ANNOTATION(26, "Android注解"),
    DESIGN_PATTERN(27, "设计模式"),
    PERMISSION(28, "权限设置"),
    IPC(29, "IPC"),
    JNI_NKD(30, "NDK JNI"),
    HOTFIX(31, "热修复技术"),
    AOP(32, "AOP编程");

    FeatureItemEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FeatureItemEnum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}