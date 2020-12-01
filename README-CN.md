# AutoJson [![as plugin](https://img.shields.io/jetbrains/plugin/d/11600-autojson.svg)](https://plugins.jetbrains.com/plugin/11600-autojson) [![GitHub stars](https://img.shields.io/github/stars/LuodiJackShen/AutoJson)](https://github.com/LuodiJackShen/AutoJson/stargazers) [![GitHub forks](https://img.shields.io/github/forks/LuodiJackShen/AutoJson)](https://github.com/LuodiJackShen/AutoJson/network)
自动生成`json_serializable`或者`jaguar_serializer`需要的代码，并自动运行`flutter pub run build_runner build —delete-conflicting-outputs`命令  
AutoJson3.0开始，会根据`pubsepc.yaml`里的依赖来判断生成`json_serializable`需要的代码还是`jaguar_serializer`需要的代码。

## 安装

依次打开 `Preferences` 和 `Plugins`(Windows: `File`、`Settings` 和 `Plugins`), 然后在Marketplace里搜索AutoJson，点击安装，最后重启Android Studio或者Idea。  

## 使用
打开dart实体类文件，点击鼠标右键，或者直接使用快捷键：`Control+Enter` (Windows:`Alt+Insert`),
你可以点击AutoJson的支持三个菜单选项，其具体含义如下：

| 菜单项 | 功能说明 | 备注 |
| ---- | ---- | ---- |
| AJ-Build Only | 只运行`flutter pub run build_runner build --delete-conflicting-outputs`命令 | AutoJson 1.8开始支持，仅支持Android Studio 4.+ |
| AJ-Generate Only | 生成`JaguarSerializer`或`json_serializable`需要的代码,不会运行命令 |  | 
| AJ-Generate and Build | 生成`JaguarSerializer`或`json_serializable`需要的代码，<br>并运行运行`flutter pub run build_runner build --delete-conflicting-outputs` 命令 | AutoJson 1.8开始支持，仅支持Android Studio 4.+ |


##### 兼容2.x版本用户的使用习惯：
你也可以依次点击Android Studio菜单栏里的'code'=>'AutoJson', 你会发现AutoJson的支持五个菜单选项，其具体含义如下：  

| 菜单项 | 功能说明 | 备注 |
| ---- | ---- | ---- |
| AutoJson-Terminal | 只运行`flutter pub run build_runner build --delete-conflicting-outputs`命令 | AutoJson 1.8开始支持，仅支持Android Studio 4.+ |
| JaguarSerializer-Terminal | 生成`JaguarSerializer`需要的代码，<br>并运行运行`flutter pub run build_runner build --delete-conflicting-outputs` 命令 | AutoJson 1.8开始支持，仅支持Android Studio 4.+ |
| JaguarSerializer | 生成`JaguarSerializer`需要的代码,不会运行命令 |  | 
| JsonSerializable-Terminal | 生成`JsonSerializable`需要的代码，<br>并运行运行`flutter pub run build_runner build --delete-conflicting-outputs` 命令 | AutoJson 1.8开始支持，仅支持Android Studio 4.+ |
| JsonSerializable | 生成`JsonSerializable`需要的代码,不会运行命令 |  |   

## 其他
有任何问题请随时提 [issue](https://github.com/LuodiJackShen/AutoJson/issues).  
如果您觉得还不错，麻烦点个小星星✨✨。

## Demo
![image](https://github.com/LuodiJackShen/AutoJson/blob/main/images/demo.gif)