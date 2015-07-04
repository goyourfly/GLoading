* 显示加载中和加载失败的库，使用非常简单

* 演示：
![加载中](https://github.com/goyourfly/GLoading/blob/master/img/loading.png?raw=true)

![加载失败](https://github.com/goyourfly/GLoading/blob/master/img/error.png?raw=true)

* 如何使用：

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ...
        GLoading gLoading = GLoading.with(this).show();
        ...
        gLoading.error();
        
        ...
        gLoading.dismiss();
    }

* 接下来要做：
- 全局配置加载中和加载出错的显示效果；
- 加载出错的提示可以随时更改；
