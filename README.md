# downloadfile
使用Retrofit2+Rxjava+Rxandroid+okhttp的方式下载文件并存储到sd卡指定目录

[![](https://jitpack.io/v/Charay/downloadfile.svg)](https://jitpack.io/#Charay/downloadfile)

使用：


gradle

Step 1.在工程build.gradle文件中加入maven地址repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. 在module的build.gradle中添加依赖，注意修改当前版本号

	dependencies {
			//注：Tag改为当前版本号,在上面JitPack后面
	        implementation 'com.github.Charay:downloadfile:Tag'
	}


Step 3.调用下面方法：

	//准备资源
		String baseUrl = "https://p3.pstatp.com/";
    	String fromUri = "weili/l/57425576771846219.jpg";
    //要存储的sd卡路径
    	String toDir = "img/girls/";
    //要存储的文件名称
    	String tofileName = "girl.jpg";

	/**
     * 下载文件到sd卡
     * @param baseUrl baseurl地址 如：https://www.baidu.com/
     * @param fromUri 文件的资源路径，包含文件名称和扩展名  如：img/bau_logo.gif
     * @param toDir  要存储在sd卡中的目录
     * @param fileName  要存储的文件名称 包含扩展名
     */
	
	DownloadFileUtil.downloadFileFromServer(baseUrl,fromUri,toDir,tofileName);
###如果添加依赖后无法编译请将自己的项目版本改成28.0.0重新编译
下面三个地方都要修改

	compileSdkVersion 28

	targetSdkVersion 28
	
	implementation 'com.android.support:appcompat-v7:28.0.0'