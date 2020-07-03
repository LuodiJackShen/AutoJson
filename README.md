# AutoJson [![as plugin](https://img.shields.io/jetbrains/plugin/d/11600-autojson.svg)](https://plugins.jetbrains.com/plugin/11600-autojson) [![GitHub stars](https://img.shields.io/github/stars/LuodiJackShen/AutoJson)](https://github.com/LuodiJackShen/AutoJson/stargazers) [![GitHub forks](https://img.shields.io/github/forks/LuodiJackShen/AutoJson)](https://github.com/LuodiJackShen/AutoJson/network)
* 作用：在flutter工程的bean类中自动生成json_serializable和jaguar_serializer需要的序列化相关代码。
* 支持：Idea、AandroidStudio

### 使用方式
#### 使用方式一(v1.1以上)
1. 依次打开 ```AndroidStudio(Idea) -> File -> Settings -> Plugin -> 搜索AutoJson -> 安装AutoJson -> 重启AndroidStudio(Idea)```。
2. 打开```.dart```文件(**v1.1可不用手动选择类名**)。
3. 点击鼠标右键，选择 ```JsonSerializable```或者```JaguarSerializer```，或者直接点击 ```Control+Enter (Windows:Alt+Insert)```。

#### 使用方式二（v1.0以上)
1. 依次打开 ```AndroidStudio(Idea) -> File -> Settings -> Plugin -> 搜索AutoJson -> 安装AutoJson -> 重启AndroidStudio(Idea)```。
2. 打开```.dart```文件，双击鼠标左键选择 ```.dart``` 文件的类名（**最新版本可不用选中类名**）。
3. 点击鼠标右键，选择 ```JsonSerializable```或者```JaguarSerializer```，或者直接点击 ```Control+Enter (Windows:Alt+Insert)```。

### 版本
* v1.5  修复部分问题；
* v1.4  终于支持jaguar_serializer了；
* v1.3  修复extends、implements关键词带来的问题；
* v1.2  修复操作图片的问题。
* v1.1  ① 体验提升：不用选中类名即可生成需要的代码;  ② 功能限制：仅在 xxx.dart 文件中可用此插件。
* v1.0  满足基本功能。

### 效果图
![image](https://github.com/LuodiJackShen/AutoJson/blob/master/image/use_guide.gif)
