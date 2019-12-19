# Android页面状态控制框架(RPageStatusController)
在Android开发中，对于需要加载网络数据的页面，一般页面都会对应多种状态，常见的有：加载中状态、网络错误状态、内容为空状态、内容状态、加载失败状态以及没有找到相关内容状态等。

对于这种需求，一般的做法是在需要有多种状态的页面的跟布局使用 `FrameLayout`，然后添加不同的状态页面，最后在代码中进行控制。当然为了编写方便，一般会将 `FrameLayout` 进行一次封装，提供动态修改状态的方法，以达到使用简单，控制方便。但是即使这样，还是需要我们在每一个需要控制状态的页面的根布局或这相关地方包裹一下，代码显得不够优雅。

基于以上问题，所以就有了这个框架，这个框架主要是为了解决了在xml中手动包裹一层布局的方式，避免重复的编码；而将这个操作移动到框架中来，而在代码中只需要进行一个简单的绑定操作即可，同时使用更加简单，控制更加方便。

## RPageStatusController框架主要包含以下功能
1. 配置全局统一的状态页面以及子控件点击事件
2. 具体页面配置自己独立的状态页面及事件
3. 状态页面重新注册点击事件(修改全局的点击事件监听，使用全局的状态页面，但是事件单独控制)
4. 注册状态页面的布局监听，可以在回调中获取到状态页面控件信息。主要针对部分特殊页面和全局的状态页面只有细微差别，避免全部重置页面的麻烦，可以在这个回调内做对应处理（比如对子控件做显示、隐藏或者修改内容操作）
5. 能绑定到Activity中、Fragment中以及View中
6. 一个页面可以使用多个绑定控制器(Activity绑定了，然后在Activity的View中可以继续绑定)

## 效果图
![状态控制](https://raw.githubusercontent.com/itrenjunhua/RPageStatusController/master/images/rPageStatusController.gif)

## 具体使用
### 配置全局的状态页面

	RPageStatusManager.getInstance()
        .addPageStatusView(RPageStatus.LOADING, R.layout.status_view_loading)
        .addPageStatusView(RPageStatus.EMPTY, R.layout.status_view_empty)
        .addPageStatusView(RPageStatus.NET_WORK, R.layout.status_view_network)
        .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error)
        // 注册全局点击监听
        .registerOnRPageEventListener(RPageStatus.ERROR, R.id.tv_error, new OnRPageEventListener() {
            @Override
            public void onViewClick(@NonNull IRPageStatusController iRPageStatusController,
                                    @RPageStatus int pageStatus, @NonNull Object object,
                                    @NonNull View view, int viewId) {
                Utils.showToast("全局配置加载错误监听: " + object);
                Log.i("MyApplication", "全局配置加载错误监听: iRPageStatusController = [" + iRPageStatusController + "]," +
                        " pageStatus = [" + pageStatus + "], object = [" + object + "], view = [" + view + "], viewId = [" + viewId + "]");
            }
        });

### 绑定到Activity中

	private RPageStatusController rPageStatusController;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);

        rPageStatusController = RPageStatusController.get();
        rPageStatusController.bind(this);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);
    }

### 绑定到Fragment中

	private RPageStatusController rPageStatusController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, null);
        rPageStatusController = RPageStatusController.get();

        View fragmentView = rPageStatusController.bind(this, view);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);

        // 这里一定要使用 bind() 方法返回的View
        return fragmentView;
    }

### 绑定到View中

	private RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        rPageStatusController = RPageStatusController.get();
        rPageStatusController.bind(findViewById(R.id.view1));
        rPageStatusController.changePageStatus(RPageStatus.LOADING);
    }

### 重置全局配置的状态页面的点击事件

	 private RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind2);

        rPageStatusController = RPageStatusController.get();
        rPageStatusController
                // 重置全局事件
                .registerOnRPageEventListener(RPageStatus.NET_WORK, R.id.tv_net_work, new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController,
											@RPageStatus int pageStatus, @NonNull Object object, 
											@NonNull View view, int viewId) {
                        // TODO
                    }
                });

		rPageStatusController.bind(this);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);
    }

### 给页面配置独立的状态页面

	private RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind2);

       	rPageStatusController = RPageStatusController.get();
       	rPageStatusController
                // 使用独立的加载错误页面
                .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error2)
                // 注册错误页面点击事件
                .registerOnRPageEventListener(RPageStatus.ERROR, false, new int[]{R.id.tv_error, R.id.tv_error2}, new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus, @NonNull Object object, @NonNull View view, int viewId) {
                        if (viewId == R.id.tv_error2){
                            // TODO
						}

                        if (viewId == R.id.tv_error) {
                            // TODO
                        }
                    }
                });

        rPageStatusController.bind(this);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);
    }

### 注册状态页面布局监听，修改当前页面错误状态页面的按钮文字

	private RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind2);

        rPageStatusController = RPageStatusController.get();
        rPageStatusController
                // 注册状态页面布局监听，修改当前页面错误状态页面的按钮文字
                .registerOnRPageViewListener(RPageStatus.ERROR, new OnRPageViewListener() {
                    @Override
                    public void onPageView(@NonNull IRPageStatusController iRPageStatusController, int pageStatus, @NonNull Object object, View statusRootView) {
                        TextView tvError2 = statusRootView.findViewById(R.id.tv_error2);
                        tvError2.setText("点我吖！！！");
                    }
                });

        rPageStatusController.bind(this);
        rPageStatusController.changePageStatus(RPageStatus.LOADING);
    }


## 混淆

	-keep class * com.renj.pagestatuscontroller.** { *; }
 	-dontwarn com.renj.pagestatuscontroller.**