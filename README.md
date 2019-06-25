# Android页面状态控制框架(RPageStatusController)
在Android开发中，对于需要加载网络数据的页面，一般页面都会对应多种状态，常见的有：加载中状态、网络错误状态、内容为空状态、内容状态、加载失败状态以及没有找到相关内容状态等。

对于这种需求，一般的做法是在需要有多种状态的页面的跟布局使用 `FrameLayout`，然后添加不同的状态页面，最后在代码中进行控制。当然为了编写方便，一般会将 `FrameLayout` 进行一次封装，提供动态修改状态的方法，以达到使用简单，控制方便。但是即使这样，还是需要我们在每一个需要控制状态的页面的根布局或这相关地方包裹一下，代码显得不够优雅。

基于以上问题，所以就有了这篇文章，这篇文章主要就是解决了在xml中来使用布局控制的方式移动到在代码中绑定和控制，使用简单，控制方便。

## RPageStatusController框架主要包含以下功能
1. 配置全局统一的状态页面以及事件控制
2. 部分页面配置独立的状态页面及事件
3. 页面重置全局点击事件(使用全局的状态页面，但是事件单独控制)
4. 能绑定到Activity中、Fragment中以及View中
5. 一个页面可以使用多个绑定控制器(Activity绑定了，然后在Activity的View中可以继续绑定)

## 效果图
![](https://raw.githubusercontent.com/itrenjunhua/RPageStatusController/master/images/rPageStatusController.gif)

## 具体使用
### 配置全局的状态页面

	RPageStatusManager.getInstance()
            .addPageStatusView(RPageStatus.LOADING, R.layout.status_view_loading)
            .addPageStatusView(RPageStatus.EMPTY, R.layout.status_view_empty)
            .addPageStatusView(RPageStatus.NET_WORK, R.layout.status_view_network, R.id.tv_net_work, null)
            .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error, R.id.tv_error, new OnRPageEventListener() {
                @Override
                public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @NonNull Object object, @NonNull View view, int viewId) {
                    Utils.showToast("全局配置加载错误监听: " + object);
                    Log.i("MyApplication", "全局配置加载错误监听：" + "object = [" + object + "], view = [" + view + "], viewId = [" + viewId + "]");
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
        rPageStatusController.bind(this);

        // 重置事件
       rPageStatusController
            .resetOnRPageEventListener(RPageStatus.NET_WORK, new OnRPageEventListener() {
                @Override
                public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @NonNull Object object, @NonNull View view, int viewId) {
                    Utils.showToast("网络错误");
					// TODO
                }
            });

        rPageStatusController.changePageStatus(RPageStatus.LOADING);
    }

### 给页面配置独立的状态页面

	private RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind2);

        rPageStatusController = RPageStatusController.get();
        rPageStatusController.bind(this);

        // 重置事件
        rPageStatusController
                // 使用独立的加载错误页面，并且页面有多个监听事件
                .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error2, new int[]{R.id.tv_error, R.id.tv_error2},false,new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @NonNull Object object, @NonNull View view, int viewId) {
                        if (viewId == R.id.tv_error2)
                            iRPageStatusController.changePageStatus(RPageStatus.CONTENT);

                        if (viewId == R.id.tv_error) {
                            iRPageStatusController.changePageStatus(RPageStatus.LOADING);
                            Utils.showToast("独立配置加载错误监听: " + object);
                            // TODO
                        }
                    }
                });

        rPageStatusController.changePageStatus(RPageStatus.LOADING);
    }

