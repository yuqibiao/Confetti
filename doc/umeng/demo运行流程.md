#demo运行流程
##android studio
###修改gradle版本
在这里将gradle的版本修改成你电脑中装的版本，不修改也可以，as会自动下载，这样打开工程会很慢

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjNfODY5X3M3OC5wbmciXV0/s78.png)

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjNfOTM1X3M3OS5wbmciXV0/s79.png)

###打开demo工程
![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjNfNzI5X3M3Ni5wbmciXV0/s76.png)

###签名文件
我们demo中默认加了签名文件，不用修改看一下就好

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMF9zODAucG5nIl1d/s80.png)


![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfNzFfczgxLnBuZyJdXQ/s81.png)
###混淆
混淆默认是开启的

	buildTypes {
        release {
            // 是否进行混淆
            minifyEnabled true
            // 混淆文件的位置
            signingConfig signingConfigs.debug
           proguardFiles 'proguard-rules.pro'
        }

        debug {
            minifyEnabled true
            signingConfig signingConfigs.debug
            proguardFiles 'proguard-rules.pro'
        }
    }

###运行demo工程
运行就可以了

###使用完整版

demo中加入的是全量集合也就是既有精简版，又有完整版，但是默认使用的是精简版

如果想看完整版的效果可以进行如下设置
在app文件中，设置使用完整版

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMTQwX3M4Mi5wbmciXV0/s82.png)


然后在微信的回调WXEntryActivity中修改import的路径

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMjA4X3M4My5wbmciXV0/s83.png)

qq在Androidmanifest文件中做下配置修改：
精简版使用这个配置：

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMjc0X3M4NC5wbmciXV0/s84.png)

完整版使用这个配置：（要把精简版的配置屏蔽掉）
![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMzQwX3M4NS5wbmciXV0/s85.png)

##eclipse

###打开工程
![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfNDRfNDZfNjEzX3M4Ni5wbmciXV0/s86.png)

###设置签名

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfNDRfNDZfNjg2X3M4Ny5wbmciXV0/s87.png)

###运行demo工程
运行就可以了

###使用完整版

demo中加入的是全量集合也就是既有精简版，又有完整版，但是默认使用的是精简版

如果想看完整版的效果可以进行如下设置
在app文件中，设置使用完整版

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMTQwX3M4Mi5wbmciXV0/s82.png)


然后在微信的回调WXEntryActivity中修改import的路径

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMjA4X3M4My5wbmciXV0/s83.png)

qq在Androidmanifest文件中做下配置修改：
精简版使用这个配置：

![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMjc0X3M4NC5wbmciXV0/s84.png)

完整版使用这个配置：（要把精简版的配置屏蔽掉）
![](http://dev.umeng.com/system/images/W1siZiIsIjIwMTYvMTEvMTAvMTZfMzFfMjRfMzQwX3M4NS5wbmciXV0/s85.png)