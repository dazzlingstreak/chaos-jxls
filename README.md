# 目的
#### 在实际项目中，需要操作excel进行导入导出，本工程旨在推荐一个强大的工具jxls来满足功能，同时demo也可以进行快速验证，减少实际工程启动-验证的耗时

* 导出可以参照 DealExportDemo，其中使用的是excel标记的方式来实现
* 处理列数据，增加自定义函数类 JxlsFunctions，允许对日期、数字、枚举值进行转换处理
* 遇到需要根据动态数据合并单元格时，增加自定义指令 MergeCommand，可以参考DealExportDemo中导出中DealExportDTO中的otherBuyerList属性
