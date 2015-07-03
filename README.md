一个简单的显示数据加载状态的库
演示：
![加载中](https://github.com/goyourfly/GLoading/blob/master/img/loading.png?raw=true)

![加载失败](https://github.com/goyourfly/GLoading/blob/master/img/error.png?raw=true)

如何使用：

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        GLoading gLoading = GLoading.with(this).show();
        ...
        gLoading.error();
        
        ...
        gLoading.dismiss();
    }
