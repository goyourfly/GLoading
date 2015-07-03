一个简单的显示数据加载状态的库
演示：

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
