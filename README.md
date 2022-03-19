# demoOne
多线程测试
1.首先需要在远程git仓库创建项目

2.通过命令 git init 可以把一个目录本地项目根目录变成git管理仓库

3. 执行命令 git remote add origin https://github.com/xxxxxxxxxxxxxxxxxxx.git 将本地仓库管理到远程仓库
4. 添加至暂存区
 git add .
6. 提交至本地仓库
 git commit -m '提交至本地仓库'

7.开始推送 
git push -u origin master
 报错
 $ git push -u origin master
error: src refspec master does not match any
error: failed to push some refs to 'https://github.com/cfrzjj/demoOne.git'

解决方法
可能需要先解决 ssl验证
 git pull --rebase origin main
 
 8. 修改本地 master分支名称 为main
 git branch -m master main

9.推送至远程分支
git push origin main:main
