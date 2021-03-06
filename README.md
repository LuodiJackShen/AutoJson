# AutoJson [![as plugin](https://img.shields.io/jetbrains/plugin/d/11600-autojson.svg)](https://plugins.jetbrains.com/plugin/11600-autojson) [![GitHub stars](https://img.shields.io/github/stars/LuodiJackShen/AutoJson)](https://github.com/LuodiJackShen/AutoJson/stargazers) [![GitHub forks](https://img.shields.io/github/forks/LuodiJackShen/AutoJson)](https://github.com/LuodiJackShen/AutoJson/network)

[English](https://github.com/LuodiJackShen/AutoJson/blob/main/README.md) | [简体中文](https://github.com/LuodiJackShen/AutoJson/blob/main/README-CN.md)

Automatically generate the code needed for `json_serializable` and `jaguar_serializer` and automatically run the `flutter pub run build_runner build -delete-conflicting-outputs` command. Makes developing flutter even easier!

## Install
Open `Preferences` and `Plugins`(Windows:`File`,`Settings` and `Plugins`), then search for AutoJson in Marketplace, click the Install button and restart Android Studio or IDEA.  

## Usage
Open the dart entity class file, right click the mouse, or just use the shortcut key: `Control+Enter` (Windows:`Alt+Insert`),    
You can click on AutoJson's support for three menu options, which have the following meanings.

| Menu | Function Description | Note |
| ---- | ---- | ---- |
| AJ-Build Only | Only run the command: `flutter pub run build_runner build --delete-conflicting-outputs` | It is supported from 1.8 and only supports Android Studio 4.+ |
| AJ-Generate Only | Only generate the code needed for `JaguarSerializer` or `json_serializable` |  | 
| AJ-Generate and Build | Generate the code needed for `JsonSerializable` or `json_serializable`, and run the `flutter Pub run build_runner build --delete-conflicting-outputs` command | It is supported from 1.8 and only supports Android Studio 4.+ |

##### Compatible with version 2.x user habits:
You can also click on 'Code' -> 'AutoJson' in the Android Studio menu bar, and you will find that AutoJson supports five menu options, which are as follows:
| Menu | Function Description | Note |
| ---- | ---- | ---- |
| AutoJson-Terminal | Only run the command: `flutter pub run build_runner build --delete-conflicting-outputs` | It is supported from 1.8 and only supports Android Studio 4.+ |
| JaguarSerializer-Terminal | Generate the code needed for `JaguarSerializer`, and run the `flutter Pub run build_runner build --delete-conflicting-outputs` command | It is supported from 1.8 and only supports Android Studio 4.+ |
| JaguarSerializer | Only generate the code needed for `JaguarSerializer` |  | 
| JsonSerializable-Terminal | Generate the code needed for `JsonSerializable`, and run the `flutter Pub run build_runner build --delete-conflicting-outputs` command | It is supported from 1.8 and only supports Android Studio 4.+ |
| JsonSerializable | Only generate the code needed for `JsonSerializable` |  |   

## Other
If you have any question, please feel free to ask [issue](https://github.com/LuodiJackShen/AutoJson/issues).  
If you feel good, please click the little star ✨✨.

## Demo
![image](https://github.com/LuodiJackShen/AutoJson/blob/main/images/demo.gif)