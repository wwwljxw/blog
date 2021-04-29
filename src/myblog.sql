/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 29/04/2021 09:57:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `views` int(11) NULL DEFAULT NULL,
  `type_id` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `comment_count` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id`) USING BTREE,
  INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id`) USING BTREE,
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES (1, b'1', b'1', '## 1. 帮助命令\r\n\r\n```\r\ndocker version //版本信息\r\ndocker info    //docker详细信息\r\ndocker --help  //类似于Linux help命令\r\n```\r\n\r\n## 2. 镜像命令\r\n\r\n### 3. 列出本地镜像\r\n\r\n```\r\ndocker images  //列出本地所有镜像\r\n```\r\n\r\n```\r\ndocker images [OPTIONS]\r\n常用OPTIONS说明：\r\n-a: 列出本地的所有镜像，包括中间印象层\r\n-q: 只显示镜像的ID\r\n--digests: 显示镜像的摘要信息\r\n--no-trunc: 显示完整的镜像信息\r\n```\r\n![](http://47.93.123.42/upload/images/20200406/1586163165546_911.png)\r\n\r\n**选项说明:**\r\n\r\n| 参数名      | 说明             |\r\n| ---------- | ---------------- |\r\n| REPOSITORY | 表示镜像的仓库源 |\r\n| TAG        | 镜像的标签       |\r\n| IMAGE ID   | 镜像ID           |\r\n| CREATED    | 镜像创建时间     |\r\n| SIZE       | 镜像大小         |\r\n\r\n同一仓库源可以有多个 TAG，代表这个仓库源的不同个版本，我们使用 REPOSITORY:TAG 来定义不同的镜像。\r\n\r\n如果你不指定一个镜像的版本标签，例如你只使用 ubuntu，docker 将默认使用 ubuntu:latest 镜像.\r\n\r\n### 2. 查找命令\r\n\r\n```\r\ndocker search [某镜像的名字] //查找某个镜像\r\n```\r\n\r\n 注意：即使本地镜像源配置的是阿里云，但是这个search也是去**docker hub**上去查找，只不过在pull的时候从阿里云上pull。 \r\n\r\n```\r\ndocker search [OPTIONS] [某镜像的名字]常用OPTIONS说明：\r\n--filter stars: 列出收藏数(stars)不少于指定值的镜像\r\n--automated: 只列出automated build类型的镜像\r\n--no-trunc: 显示完整的镜像信息\r\n```\r\n\r\n![](http://47.93.123.42/upload/images/20200406/1586163209506_87.png)\r\n\r\n```\r\ndocker pull [镜像名字][:TAG] //下载某个镜像\r\n```\r\n\r\n 注意：docker pull tomcat 相当于是docker pull tomcat:latest，不加后面的TAG的话默认是latest ,如果需要具体的版本还需要注明具体pull那个版本,否则则是最新版本.\r\n\r\n### 3. 删除命令\r\n\r\n```\r\ndocker rmi [某个镜像的名字/ID]		//删除某个镜像\r\ndocker rmi -f [某个镜像的名字/ID]	//守护进程正在使用的情况下可以用-f强制删除\r\ndocker rmi -f [镜像名1][:TAG] [镜像名2][:TAG] //删除多个镜像\r\ndocker rmi -f $(docker images -qa) //删除全部 $()是shell脚本中的变量返回值的赋值\r\n```\r\n\r\n## 3.容器命令\r\n\r\n### 1. 新建并启动容器\r\n\r\n```\r\ndocker run [OPTIONS] IMAGE [COMMAND] [ARG...] //优先查找本地如果本地没有则从hub pull一个镜像\r\n```\r\n\r\n```\r\nOPTIONS说明（常用）：有些是一个减号，有些是两个减号\r\n--name=\"容器新名字\": 为容器指定一个名称；\r\n-d: 后台运行容器，并返回容器ID，也即启动守护式容器；\r\n-i：以交互模式运行容器，通常与 -t 同时使用；\r\n-t：为容器重新分配一个伪输入终端，通常与 -i 同时使用；\r\n-P: 随机端口映射；\r\n-p: 指定端口映射，有以下四种格式\r\n      ip:hostPort:containerPort\r\n      ip::containerPort\r\n      hostPort:containerPort\r\n      containerPort\r\n```\r\n\r\n **启动交互模式** :\r\n\r\n```\r\n docker run -it [镜像名]\r\n```\r\n\r\n### 2.  列出所有当前正在运行的容器 \r\n\r\n```\r\ndocker ps //当前正在运行的容器\r\n```\r\n\r\n```\r\nOPTIONS说明（常用）：\r\n-a :列出当前所有正在运行的容器+历史上运行过的\r\n-l :显示最近创建的容器。\r\n-n：显示最近n个创建的容器。\r\n-q :静默模式，只显示容器编号。\r\n--no-trunc :不截断输出。\r\n```\r\n\r\n### 3. 退出容器\r\n\r\n``` \r\nexit 		 //容器退出停止\r\ncrtl + P + Q //容器退出不停止\r\n```\r\n\r\n### 4. 启动守护式容器\r\n\r\n```\r\ndocker run -d [容器名]\r\n```\r\n\r\n**问题**：然后docker ps -a 进行查看, 会发现容器已经退出\r\n\r\n很重要的要说明的一点: Docker容器后台运行,就必须有一个前台进程.\r\n\r\n容器运行的命令如果不是那些一直挂起的命令（比如运行top，tail），就是会自动退出的。\r\n\r\n 这个是docker的机制问题,比如你的web容器,我们以nginx为例，正常情况下,我们配置启动服务只需要启动响应的service即可。例如\r\n\r\nservice nginx start\r\n\r\n但是,这样做,nginx为后台进程模式运行,就导致docker前台没有运行的应用,\r\n\r\n这样的容器后台启动后,会立即自杀因为他觉得他没事可做了.\r\n\r\n所以，最佳的解决方案是,将你要运行的程序以前台进程的形式运行\r\n\r\n### 5. 查看容器日志\r\n\r\n```\r\ndocker logs -f -t -tail [tail numbers] [容器ID]\r\n```\r\n\r\n```\r\n相关OPTIONS说明(常用)：\r\n-t:显示时加入时间戳\r\n-f:跟随最新的日志打印\r\n-tail [numbers]:显示最后的多少条\r\n```\r\n\r\n### 6. 查看容器内运行的进程 \r\n\r\n```\r\ndocker top [容器ID]\r\n```\r\n![](http://47.93.123.42/upload/images/20200406/1586163299105_284.png)\r\n\r\n### 7. 进入正在运行的容器\r\n\r\n```\r\ndocker attach [容器ID] //重新进入正在运行的容器中\r\ndocker exec -it [容器ID] [shell command] //不进入容器，直接在宿主机上运行容器里的shell command，并返回结果。\r\n```\r\n\r\n**区别**:\r\n\r\n`attach`:是直接进入容器,不启动新的进程\r\n\r\n`exec`:是在容器中打开新的终端,并且启动新的进程\r\n\r\n### 8.  文件拷贝\r\n\r\n```\r\ndocker cp [容器ID]:[容器内的路径] [目的主机的路径]\r\n```\r\n\r\n### 3. 其他命令\r\n\r\n```\r\ndocker start [容器ID/容器名]：启动容器\r\n\r\ndocker restart [容器ID/容器名]：重新启动容器\r\n\r\ndocker stop[容器ID/容器名]：停止容器（正常关机)\r\n\r\ndocker rm[容器ID]：删除已停止的容器\r\n\r\ndocker rm -f $(docker ps -a -q)：一次性删除多个容器\r\n\r\ndocker ps -a -q | xargs docker rm：一次性删除多个容器(Linux管道)\r\n\r\ndocker ps -n 2 : 2代表运行过的近二个容器\r\n\r\ndocker inspect [容器ID]：从查看容器内部细节\r\n\r\nattach Attach to a running container # 当前 shell 下 attach 连接指定运行镜像\r\n\r\nbuild Build an image from a Dockerfile # 通过 Dockerfile 定制镜像\r\n\r\ncommit Create a new image from a container changes # 提交当前容器为新的镜像\r\n\r\ncp Copy files/folders from the containers filesystem to the host path #从容器中拷贝指定文件或者目录到宿主机中\r\n\r\ncreate Create a new container # 创建一个新的容器，同 run，但不启动容器\r\n\r\ndiff Inspect changes on a container\'s filesystem # 查看 docker 容器变化\r\n\r\nevents Get real time events from the server # 从 docker 服务获取容器实时事件\r\n\r\nexec Run a command in an existing container # 在已存在的容器上运行命令\r\n\r\nexport Stream the contents of a container as a tar archive # 导出容器的内容流作为一个 tar 归档文件[对应 import ]\r\n\r\nhistory Show the history of an image # 展示一个镜像形成历史\r\n\r\nimages List images # 列出系统当前镜像\r\n\r\nimport Create a new filesystem image from the contents of a tarball # 从tar包中的内容创建一个新的文件系统映像[对应export]\r\n\r\ninfo Display system-wide information # 显示系统相关信息\r\n\r\ninspect Return low-level information on a container # 查看容器详细信息\r\n\r\nkill Kill a running container # kill 指定 docker 容器\r\n\r\nload Load an image from a tar archive # 从一个 tar 包中加载一个镜像[对应 save]\r\n\r\nlogin Register or Login to the docker registry server # 注册或者登陆一个 docker 源服务器\r\n\r\nlogout Log out from a Docker registry server # 从当前 Docker registry 退出\r\n\r\nlogs Fetch the logs of a container # 输出当前容器日志信息\r\n\r\nport Lookup the public-facing port which is NAT-ed to PRIVATE_PORT # 查看映射端口对应的容器内部源端口\r\n\r\npause Pause all processes within a container # 暂停容器\r\n\r\nps List containers # 列出容器列表\r\n\r\npull Pull an image or a repository from the docker registry server # 从docker镜像源服务器拉取指定镜像或者库镜像\r\n\r\npush Push an image or a repository to the docker registry server # 推送指定镜像或者库镜像至docker源服务器\r\n\r\nrestart Restart a running container # 重启运行的容器\r\n\r\nrm Remove one or more containers # 移除一个或者多个容器\r\n\r\nrmi Remove one or more images # 移除一个或多个镜像[无容器使用该镜像才可删除，否则需删除相关容器才可继续或 -f 强制删除]\r\n\r\nrun Run a command in a new container # 创建一个新的容器并运行一个命令\r\n\r\nsave Save an image to a tar archive # 保存一个镜像为一个 tar 包[对应 load]\r\n\r\nsearch Search for an image on the Docker Hub # 在 docker hub 中搜索镜像\r\n\r\nstart Start a stopped containers # 启动容器\r\n\r\nstop Stop a running containers # 停止容器\r\n\r\ntag Tag an image into a repository # 给源中镜像打标签\r\n\r\ntop Lookup the running processes of a container # 查看容器中运行的进程信息\r\n\r\nunpause Unpause a paused container # 取消暂停容器\r\n\r\nversion Show the docker version information # 查看 docker 版本号\r\n\r\nwait Block until a container stops, then print its exit code # 截取容器停止时的退出状态值\r\n\r\n```\r\n\r\n------------\r\n\r\n\r\n### 参考\r\n**尚硅谷_Docker核心技术（基础篇）**', '2020-04-05 21:27:25', 'Docker 常用命令记录', 'https://images.weserv.nl/?url=https://i0.hdslb.com/bfs/article/8cd0e51177f4b9e93962881016a68c5558fde686.jpg', '转载', b'1', b'1', b'1', 'Docker 常用命令', '2021-04-01 02:08:26', 45, 3, 1, 0);
INSERT INTO `t_blog` VALUES (2, b'1', b'1', '## MyISAM和InnoDB区别\r\n\r\n​	MyISAM是MySQL的默认数据库引擎（5.5版之前）。虽然性能极佳，而且提供了大量的特性，包括全文索引、压缩、空间函数等，但MyISAM不支持事务和行级锁，而且最大的缺陷就是崩溃后无法安全恢复。不过，5.5版本之后，MySQL引入了InnoDB（事务性数据库引擎），MySQL 5.5版本后默认的存储引擎为InnoDB。\r\n\r\n​	大多数时候我们使用的都是 InnoDB 存储引擎，但是在某些情况下使用 MyISAM 也是合适的比如读密集的情况下。（如果你不介意 MyISAM 崩溃恢复问题的话）。\r\n\r\n**具体区别**\r\n\r\n- **是否支持行级锁 :** MyISAM 只有表级锁(table-level locking)，而InnoDB 支持行级锁(row-level locking)和表级锁,默认为行级锁。\r\n- **是否支持事务和崩溃后的安全恢复：** MyISAM 强调的是性能，每次查询具有原子性,其执行速度比InnoDB类型更快，但是不提供事务支持。但是InnoDB 提供事务支持事务，外部键等高级数据库功能。 具有事务(commit)、回滚(rollback)和崩溃修复能力(crash recovery capabilities)的事务安全(transaction-safe (ACID compliant))型表。\r\n- **是否支持外键：** MyISAM不支持，而InnoDB支持。\r\n- **是否支持MVCC：** 仅InnoDB 支持。应对高并发事务, MVCC比单纯的加锁更高效;MVCC只在 READ COMMITTED 和 REPEATABLE READ 两个隔离级别下工作;MVCC可以使用 乐观(optimistic)锁 和 悲观(pessimistic)锁来实现;各数据库中MVCC实现并不统一。\r\n\r\n   MyISAM适合：(1) 做很多count 的计算；(2) 读密集；(3) 没有事务；\r\n   InnoDB适合：(1) 要求事务；(2)写密集 ；(3) 高并发；\r\n\r\n## 事物\r\n\r\n### 什么是事务?\r\n\r\n​	事务是逻辑上的一组操作，**要么都执行，要么都不执行**。\r\n\r\n###          事物的四大特性 \r\n\r\n- 原子性（Atomicity）： 事务是最小的执行单位，不允许分割。事务的原子性确保动作要么全部完成，要么完全不起作用；\r\n- 一致性（Consistency）： 执行事务前后，数据保持一致，多个事务对同一个数据读取的结果是相同的；\r\n- 隔离性（Isolation）： 并发访问数据库时，一个用户的事务不被其他事务所干扰，各并发事务之间数据库是独立的；\r\n- 持久性（Durability）： 一个事务被提交之后。它对数据库中数据的改变是持久的，即使数据库发生故障也不应该对其有任何影响。\r\n\r\n### 并发事务带来哪些问题?\r\n\r\n- 脏读（Dirty read）: 一个事务读取了另一个事务修改但未提交的数据。\r\n- 丢失修改（Lost to modify）: 一个事务连续读两次数据，但结果不一样。(两次读之间，数据被其他事务修改)。\r\n- 不可重复读（Unrepeatableread）: 指在一个事务内多次读同一数据。在这个事务还没有结束时，另一个事务也访问该数据。那么，在第一个事务中的两次读数据之间，由于第二个事务的修改导致第一个事务两次读取的数据可能不太一样。这就发生了在一个事务内两次读到的数据是不一样的情况，因此称为不可重复读。\r\n- 幻读（Phantom read）: 一个事务连续读两次数据，读取数据量不一样。(两次读之前，数据被其他事务删除或新增)。\r\n\r\n### 事务隔离级别\r\n\r\n- 读未提交: 可以读取尚未提交的数据。 **可能会导致脏读、幻读或不可重复读**。 \r\n- 读已提交: 允许读取并发事务已经提交的数据。 **可以阻止脏读，但是幻读或不可重复读仍有可能发生**。 \r\n- 可重复读: 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止脏读和不可重复读，但幻读仍有可能发生。\r\n  不可重复读很容易让人陷入一个思维定式那就是 我干嘛需要多次读取一个值还要保证一致要跳出这个思维看本质：我在事务中会不会受到其他事务的影响？\r\n  举个简单的例子 数据校对（只是举个例子体现意思 不用太在意具体的业务）\r\n  我要取当前的余额 当前的账单 上个月的余额 我要检验一下数据对不对\r\n  我在事务中取了当前的账单和上个月的余额，好嘛，这时候又有新的订单提交了，我再获取余额是不是就不一致了？\r\n- 串行化: 最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说， **该级别可以防止脏读、不可重复读以及幻读**。 	\r\n\r\n​    MySQL InnoDB默认支持可重复读，但使用了Next-Key Lock算法避免了幻读的发生。完全达到了保保证事务的隔离要求。但在分布式事务下，一般可串行化。\r\n\r\n​	设置隔离级别之后，并不是不能并发，而是并发的时候，一个事务的修改数据(绝对读到，提交的才能读到。提交不提交，更新的数据都读不到。提交不提交，增删的数据都读不到)，什么时候才能被另一个事务读到。但彼此的逻辑操作没有影响。\r\n\r\n## 锁机制\r\n\r\nMysql为了解决并发、数据安全的问题，使用了锁机制。\r\n\r\n可以按照锁的粒度把数据库锁分为**表级锁和行级锁**。\r\n\r\n### 按照锁的粒度分类\r\n\r\n- 表级锁\r\n\r\nMysql中锁定 **粒度最大** 的一种锁，对当前操作的整张表加锁，实现简单 ，**资源消耗也比较少，加锁快，不会出现死锁 。**其锁定粒度最大，触发锁冲突的概率最高，并发度最低，MyISAM和 InnoDB引擎都支持表级锁。\r\n\r\n- 行级锁\r\n\r\nMysql中锁定 **粒度最小** 的一种锁，只针对当前操作的行进行加锁。 行级锁能大大减少数据库操作的冲突。其加锁粒度最小，并发度高，**但加锁的开销也最大，加锁慢，会出现死锁。** InnoDB支持的行级锁，包括如下几种。\r\n\r\n- Record Lock: 对索引项加锁，锁定符合条件的行。其他事务不能修改和删除加锁项；\r\n- Gap Lock: 对索引项之间的“间隙”加锁，锁定记录的范围（对第一条记录前的间隙或最后一条将记录后的间隙加锁），不包含索引项本身。其他事务不能在锁范围内插入数据，这样就防止了别的事务新增幻影行。\r\n- Next-key Lock： 锁定索引项本身和索引范围。即Record Lock和Gap Lock的结合。可解决幻读问题。\r\n\r\n **虽然使用行级索具有粒度小、并发度高等特点，但是表级锁有时候也是非常必要的**： \r\n\r\n- 事务更新大表中的大部分数据直接使用表级锁效率更高；\r\n- 事务比较复杂，使用行级索很可能引起死锁导致回滚。\r\n\r\n **表级锁和行级锁可以进一步划分为共享锁（s）和排他锁（X）。** \r\n\r\n### 按照是否可写分类\r\n\r\n**共享锁（s）**\r\n\r\n**共享锁（Share Locks，简记为S）又被称为读锁**，其他用户可以并发读取数据，但任何事务都不能获取数据上的排他锁，直到已释放所有共享锁。\r\n\r\n共享锁(S锁)又称为读锁，若事务T对数据对象A加上S锁，则事务T只能读A；其他事务只能再对A加S锁，而不能加X锁，直到T释放A上的S锁。这就保证了其他事务可以读A，但在T释放A上的S锁之前不能对A做任何修改。 \r\n\r\n **排他锁（X）：** \r\n\r\n排它锁（(Exclusive lock,简记为X锁)）又称为写锁，若事务T对数据对象A加上X锁，则只允许T读取和修改A，其它任何事务都不能再对A加任何类型的锁，直到T释放A上的锁。它防止任何其它事务获取资源上的锁，直到在事务的末尾将资源上的原始锁释放为止。在更新操作(INSERT、UPDATE 或 DELETE)过程中始终应用排它锁。\r\n\r\n**两者之间的区别：**\r\n\r\n1. 共享锁（S锁）：如果事务T对数据A加上共享锁后，则其他事务只能对A再加共享锁，不 能加排他锁。获取共享锁的事务只能读数据，不能修改数据。\r\n2. 排他锁（X锁）：如果事务T对数据A加上排他锁后，则其他事务不能再对A加任任何类型的封锁。获取排他锁的事务既能读数据，又能修改数据。\r\n\r\n### 死锁和避免死锁 \r\n\r\nMyISAM中是不会产生死锁的，因为MyISAM总是一次性获得所需的全部锁，要么全部满足，要么全部等待。而在InnoDB中，锁是逐步获得的，就造成了死锁的可能。\r\n\r\n在MySQL中，行级锁并不是直接锁记录，而是锁索引。索引分为主键索引和非主键索引两种，如果一条SQL语句操作了主键索引，MySQL就会锁定这条主键索引；如果一条语句操作了非主键索引，MySQL会先锁定该非主键索引，再锁定相关的主键索引。 在UPDATE、DELETE操作时，MySQL不仅锁定WHERE条件扫描过的所有索引记录，而且会锁定相邻的键值，即所谓的next-key locking。\r\n\r\n当两个事务同时执行，一个锁住了主键索引，在等待其他相关索引。另一个锁定了非主键索引，在等待主键索引。这样就会发生死锁。\r\n\r\n发生死锁后，InnoDB一般都可以检测到，并使一个事务释放锁回退，另一个获取锁完成事务。\r\n\r\n有多种方法可以避免死锁，这里只介绍常见的三种\r\n\r\n1. 如果不同程序会并发存取多个表，尽量约定以相同的顺序访问表，可以大大降低死锁机会。\r\n2. 在同一个事务中，尽可能做到一次锁定所需要的所有资源，减少死锁产生概率；\r\n3. 对于非常容易产生死锁的业务部分，可以尝试使用升级锁定颗粒度，通过表级锁定来减少死锁产生的概率\r\n\r\n---\r\n### 参考\r\n**部分内容转载自Guide博客**\r\n\r\n', '2020-07-11 00:14:58', 'MySQL存储引擎,事务,锁机制总结。', 'http://47.93.123.42:80/upload/images/20200715/1594793427645_376.jpg', '转载', b'0', b'0', b'1', '数据库知识点复习', '2021-04-01 02:08:40', 37, 3, 1, 0);
INSERT INTO `t_blog` VALUES (3, b'1', b'1', '## 121. 买卖股票的最佳时机\r\n\r\n给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。\r\n\r\n如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。\r\n\r\n注意：你不能在买入股票前卖出股票。\r\n\r\n```\r\nclass Solution {\r\n    public int maxProfit(int[] prices) {\r\n        int cost = Integer.MAX_VALUE;\r\n        int profit = 0;\r\n\r\n        for(int price : prices){\r\n            cost = Math.min(cost,price);\r\n            profit = Math.max(profit, price - cost);\r\n        }\r\n\r\n        return profit;\r\n    }\r\n}\r\n```\r\n## 35. 搜索插入位置\r\n\r\n给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。\r\n\r\n你可以假设数组中无重复元素。\r\n\r\n示例 1:\r\n\r\n```\r\n输入: [1,3,5,6], 5\r\n输出: 2\r\n```\r\n\r\n\r\n示例 2:\r\n\r\n```\r\n输入: [1,3,5,6], 2\r\n输出: 1\r\n```\r\n\r\n\r\n示例 3:\r\n\r\n```\r\n输入: [1,3,5,6], 7\r\n输出: 4\r\n```\r\n\r\n\r\n示例 4:\r\n\r\n```\r\n输入: [1,3,5,6], 0\r\n输出: 0\r\n```\r\n\r\n```\r\nclass Solution {\r\n    public int searchInsert(int[] nums, int target) {\r\n        int start = 0, end = nums.length - 1;\r\n        while(start <= end){\r\n            int mid = (start + end) >> 1;\r\n            if(nums[mid] < target){\r\n                start = mid + 1;\r\n            } else if(nums[mid] == target){\r\n               return mid;\r\n            } else {\r\n               end = mid - 1;\r\n            }\r\n        }\r\n        return start;\r\n    }\r\n}\r\n```\r\n## 1115. 交替打印FooBar\r\n我们提供一个类：\r\n```\r\nclass FooBar {\r\n  public void foo() {\r\n    for (int i = 0; i < n; i++) {\r\n      print(\"foo\");\r\n    }\r\n  }\r\n\r\n  public void bar() {\r\n    for (int i = 0; i < n; i++) {\r\n      print(\"bar\");\r\n    }\r\n  }\r\n}\r\n```\r\n两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。\r\n\r\n请设计修改程序，以确保 \"foobar\" 被输出 n 次。\r\n示例 1:\r\n```\r\n输入: n = 1\r\n输出: \"foobar\"\r\n解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，\"foobar\" 将被输出一次。\r\n```\r\n```\r\nclass FooBar {\r\n    private int n;\r\n\r\n    Semaphore s1 = new Semaphore(1);\r\n    Semaphore s2 = new Semaphore(0);\r\n\r\n    public FooBar(int n) {\r\n        this.n = n;\r\n    }\r\n\r\n    public void foo(Runnable printFoo) throws InterruptedException {\r\n        \r\n        for (int i = 0; i < n; i++) {\r\n            s1.acquire();\r\n        	printFoo.run();\r\n            s2.release();\r\n        }\r\n    }\r\n\r\n    public void bar(Runnable printBar) throws InterruptedException {\r\n        \r\n        for (int i = 0; i < n; i++) {\r\n            s2.acquire();\r\n        	printBar.run();\r\n            s1.release();\r\n        }\r\n    }\r\n}\r\n```\r\n补充一个交替打印奇偶:\r\n```\r\npublic class FooBar {\r\n	static int i = 0;\r\n	public static void main(String[] args) throws InterruptedException {\r\n\r\n		Semaphore Odd = new Semaphore(1);\r\n		Semaphore even = new Semaphore(0);\r\n\r\n		Thread t1 = new Thread(() -> {\r\n			try {\r\n				Odd.acquire();\r\n				while (true) {\r\n					i++;\r\n					if (i % 2 == 1){\r\n						System.out.println(\"奇数线程:\" + \" \" + i);\r\n						even.release();\r\n						Odd.acquire();\r\n					}\r\n				}\r\n			} catch (InterruptedException e) {\r\n				e.printStackTrace();\r\n			}\r\n		});\r\n\r\n		Thread t2 = new Thread(() -> {\r\n			try {\r\n				even.acquire();\r\n				while (true) {\r\n					i++;\r\n					if (i % 2 == 0){\r\n						System.out.println(\"偶数线程:\" + \" \" + i);\r\n						Odd.release();\r\n						even.acquire();\r\n					}\r\n				}\r\n			} catch (InterruptedException e) {\r\n				e.printStackTrace();\r\n			}\r\n		});\r\n\r\n		t2.start();\r\n		t1.start();\r\n	}\r\n}\r\n```\r\n## 剑指 Offer 31. 栈的压入、弹出序列\r\n输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。\r\n```\r\nclass Solution {\r\n    public boolean validateStackSequences(int[] pushed, int[] popped) {\r\n        Stack<Integer> stack = new Stack<>();\r\n        int i = 0;\r\n        for(int p : pushed){\r\n            stack.add(p);\r\n            while(!stack.isEmpty() && stack.peek() == popped[i]){\r\n                stack.pop();\r\n                i++;\r\n            }\r\n        }\r\n        return stack.isEmpty();\r\n    }\r\n}\r\n```\r\n\r\n', '2020-07-12 15:47:40', '记录下刷题过程,后续给博客加个目录方便查找!', 'http://47.93.123.42:80/upload/images/20200716/1594921730619_445.png', '转载', b'0', b'1', b'1', 'LeetCode', '2021-04-01 02:08:20', 15, 62, 1, 0);
INSERT INTO `t_blog` VALUES (5, b'1', b'1', '## 什么是内存溢出和内存泄漏\r\n\r\n- **内存溢出 OutOfMemory** ：指程序在申请内存时，没有足够的内存空间供其使用。\r\n- **内存泄露 Memory Leak**：指程序在申请内存后，无法释放已申请的内存空间，内存泄漏最终将导致内存溢出。\r\n\r\n## 如何分析内存溢出或者泄露呢？\r\n\r\n可以使用 JDK自带的工具jvisualvm，因为是JDK自带工具配置配置好环境变量，在Windows系统下使用 Win  + R快捷键输入jvisualvm就可以直接打开了。\r\n\r\n![](http://47.93.123.42:80/upload/images/20200717/1594991972143_788.png)\r\n\r\n本文以一段Java代码为例， 写一个循环一直往List丢数据，最终导致内存溢出。\r\n\r\n```\r\nclass Test {\r\n    public static void main(String[] args) {\r\n        int size = 1204 * 1024 * 8;\r\n        List<byte[]> list = new ArrayList<>();\r\n        for (int i = 0; i < 1000; i++) {\r\n            try {\r\n                Thread.sleep(100);\r\n            } catch (InterruptedException e) {\r\n                e.printStackTrace();\r\n            }\r\n            list.add(new byte[size]);\r\n        }\r\n    }\r\n}\r\n```\r\n\r\n### 运行后jvisualvm分析界面：\r\n\r\n![](http://47.93.123.42:80/upload/images/20200717/1594991988308_626.png)\r\n\r\n可以看到堆内存一直在上升，且没有被回收。\r\n\r\n### 生成Dump文件\r\n\r\n**参数方式（推荐方式）：**\r\n\r\n1. 通过 -XX:+HeapDumpOnOutOfMemoryError 参数来生成dump文件（为产生OOM，还使用了-Xms20m -Xmx20m参数）；\r\n2. 使用Memory Analyzer打开生成的dump文件。“File->Open heap dump...”打开指定的dump文件后，将会生成Overview选项。\r\n3. ![](http://47.93.123.42:80/upload/images/20200717/1594992030137_60.png)\r\n\r\njvisualvm方式：\r\n\r\n![](http://47.93.123.42:80/upload/images/20200717/1594992004375_191.jpg)\r\n\r\n## 分析Dump文件并找到定位原因\r\n\r\n###  内存分析工具\r\n\r\nMAT(Memory Analyzer Tool)，一个基于Eclipse的内存分析工具，是一个快速、功能丰富的JAVA heap分析工具，它可以帮助我们查找内存泄漏和减少内存消耗。使用内存分析工具从众多的对象中进行分析，快速的计算出在内存中对象的占用大小，看看是谁阻止了垃圾收集器的回收工作，并可以通过报表直观的查看到可能造成这种结果的对象。\r\n\r\n###  **如何使用MAT** \r\n\r\n通过MAT打开Dump文件\r\n\r\n![](http://47.93.123.42:80/upload/images/20200717/1594992060570_609.png)\r\n\r\n 在Overview选项中，以饼状图的形式列举出了程序内存消耗的一些基本信息，其中每一种不同颜色的饼块都代表了不同比例的内存消耗情况。下方有几个选项如图： \r\n![](http://47.93.123.42:80/upload/images/20200717/1594992050076_114.png)\r\n- Histogram  可以列出内存中的对象，对象的个数以及大小；\r\n- Dominator Tree  可以列出那个线程，以及线程下面的那些对象占用的空间；\r\n- Top consumers  通过图形列出最大的object； \r\n- Leak Suspects  通过MA自动分析泄漏的原因。\r\n\r\n上面MAT已经帮我们分析出了问题，点进去会发现是 ArrayList 的问题\r\n\r\n![](http://47.93.123.42:80/upload/images/20200717/1594992086587_771.png)\r\n\r\n往下翻会发现已经帮我们定位了错误的代码行数\r\n\r\n![](http://47.93.123.42:80/upload/images/20200717/1594992073328_324.png)\r\n\r\n## 总结\r\n\r\n通过内存映像工具（例如Eclipse Memory Analyzer)对dump出来的堆转储快照进行分析，重点是确认内存中的对象是否必要，也要弄清是出现了内存泄漏还是内存溢出。\r\n如果是内存泄漏，可进一步通过工具查看泄露对象到GC ROOT的引用链信息，定位出泄露代码的位置。\r\n如果不存在泄漏，就要调整虚拟机的堆参数（-Xmx与-Xms），然后从代码上检查是否存在某些对象生命周期过长，持有状态时间过长的情况。\r\n---《深入理解JVM：JVM一般特性和不错实践》\r\n\r\n', '2020-07-17 13:21:58', 'OOM的分析过程。', 'http://47.93.123.42:80/upload/images/20200717/1594994061069_257.jpg', '原创', b'1', b'1', b'0', '如何排查堆内存溢出(OOM)?', '2021-04-01 02:00:07', 30, 3, 1, 0);
INSERT INTO `t_blog` VALUES (6, b'0', b'1', '# String类型\r\n\r\n字符串类型是 Redis 最基础的数据结构，首先键都是字符串类型， Value 不仅是 String，也可以是数字。常用在缓存、计数、共享Session、限速等。\r\n\r\n- `set key value`增加数据\r\n- `get key`获得数据\r\n- `append key value`追加字符串，如果当前key不存在，则等同于`set key value`\r\n- `strlen key`获取字符串长度\r\n- `keys *`获取所有key\r\n- `exists key`判断某个key是否存在\r\n- `incr key`自增+1，等同于`i++`\r\n- `decr key`自减-1，等同于`i—`\r\n- `incrby key 5`步长，指定增量，等同于`i+=5`\r\n- `decrby key 5`步长，指定减量，等同于`i-=5`\r\n- `getrange key start end`截取字符串\r\n- `setrange key offset value`替换字符串\r\n- `setex key seconds value`设置过期时间\r\n- `setnx key value`不存在再设置(分布式锁会经常用到)\r\n- `mset key value [key value ...]`批量增加数据\r\n- `msetnx key value [key value ...]`**不存在**则批量增加数据，该命令为原子性操作\r\n\r\n![](https://img-blog.csdnimg.cn/20210129175159455.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZhbmppYW5oYWk=,size_16,color_FFFFFF,t_70)\r\n\r\n![](https://img-blog.csdnimg.cn/20210129175758791.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2ZhbmppYW5oYWk=,size_16,color_FFFFFF,t_70)\r\n\r\n---\r\n\r\n# List（列表）类型\r\n\r\n列表（list）类型是用来存储多个有序的字符串。可以做简单的消息队列的功能。 数据结构：List 就是链表，可以用来当消息队列用。Redis 提供了 List 的 Push 和 Pop 操作，还提供了操作某一段的 API，可以直接查询或者删除某一段的元素。 实现方式：Redis List 的是实现是一个双向链表，既可以支持反向查找和遍历，更方便操作，不过带来了额外的内存开销。 \r\n\r\n- `lpush key element [element ...]`增加List数组\r\n- `lrange key start stop`读取list数组\r\n- `lindex key index`读取指定数组指定下标的元素\r\n- `llen key`获取数组长度\r\n- `rpush key element [element ...]`往指定数组末尾添加数据\r\n- `lpop key`移除指定数组的第一个元素\r\n- `rpop key`移除指定数组的最后一个元素\r\n- `lrem key count element`移除数组中指定的元素\r\n- `ltrim key start stop`通过下标截取指定长度，执行后，该list只剩下被截取的部分。\r\n- `rpoplpush source destination`移除列表的最后一个元素，将其移动到新的数组中。\r\n- `lset key index element`更新指定下标元素中的数据。\r\n- `linsert key BEFORE|AFTER pivot element`将某个具体的value插入到列表中某个元素的后面或者前面\r\n\r\n## 小结\r\n\r\n- redis中的list实际上是一个链表，before Node after，left，right都可以插入。\r\n- 如果key不存在，将创建新的链表。\r\n- 如果key存在，新增内容。\r\n- 如果移除了所有值，空链表，也代表不存在。\r\n- 在两边插入或者改动值，效率最高。中间插入或改动运输，相对来说效率较低。\r\n---\r\n\r\n# Set（集合）类型\r\n\r\n集合（set）类型也是用来保存多个的字符串元素，集合是通过 hashtable 实现的。 但和列表类型不一样的是，集合中不允许有重复元素，并且集合中的元素是无序的，不能通过索引下标获取元素。利用 Set 的交集、并集、差集等操作，可以计算共同喜好，全部的喜好，自己独有的喜好等功能。\r\n\r\n- `sadd key member [member ...]`往集合中添加元素，如果集合不存在则创建集合。\r\n- `smembers key`遍历集合\r\n- `sismember key member`判断某个元素是否存在于集合中\r\n- `scard key`获取集合中元素个数\r\n- `srem key member [member ...]`移除集合中指定的元素。\r\n- `srandmember key [count]`随机获取Set集合中的值。\r\n- `spop key [count]`随机删除set集合中的元素。\r\n- `smove source destination member`移动指定元素到另一集合中。\r\n- `sdiff key [key ...]`查看集合间的差集，即不共有的元素。\r\n- `sinter key [key ...]`查看集合间的交集，即共有的元素。\r\n- `sunion key [key ...]`查看集合间的并集，即集合间所有的元素。\r\n\r\n---\r\n\r\n# Hash类型\r\n\r\n在Redis中，哈希类型是指键值本身又是一个键值对结构，哈希可以用来存放用户信息，比如实现购物车。\r\n![](https://img-blog.csdnimg.cn/20210129195151621.png)\r\n\r\n- `hmset key field value [field value ...]`往指定的hash中添加元素，如果该hash不存在则创建hash。\r\n- `hmget key field [field ...]`获取hash集合中指定key的元素。\r\n- `hgetall key`获取hash中所有的k/v。\r\n- `hdel key field [field ...]`删除hash中指定的键。\r\n- `hlen key`获取键值对个数。\r\n- `hexists key field`判断hash指定key是否存在。\r\n- `hkeys key`获取所有的key。\r\n- `hvals key`获取所有的value。\r\n- `hincrby key field increment`增量运算。\r\n- `hsetnx key field value`该字段**不存在**则增加字段。\r\n\r\n---\r\n\r\n# Zset有序集合\r\n\r\n：Sorted Set 多了一个权重参数 Score，集合中的元素能够按 Score 进行排列。实现方式：Redis Sorted Set 的内部使用 HashMap 和跳跃表（skipList）来保证数据的存储和有序，HashMap 里放的是成员到 Score 的映射。\r\n\r\n- `zadd key [NX|XX] [CH] [INCR] score member [score member ...]`添加元素\r\n- `zrange key start stop [WITHSCORES]`升序\r\n- `zrevrange key start stop [WITHSCORES]`降序\r\n- `zrangebyscore key min max [WITHSCORES] [LIMIT offset count]`获取所有元素（包括score）\r\n- `zcard key`获取zset长度\r\n- `zcount key min max`获取score指定区间的元素个数\r\n- `zrem key member [member ...]`移除指定元素。\r\n', '2021-03-29 17:21:33', 'Redis五大类型常用命令', 'https://note.youdao.com/yws/api/personal/file/F2E7621E7031434BBA8413D840C70F94?method=download&shareKey=475b1bba037f6d72505ea3e3af02ce07', '原创', b'1', b'1', b'1', 'Redis五大类型常用命令', '2021-04-01 02:08:33', 42, 60, 1, 2);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `blog_id` bigint(20) NULL DEFAULT NULL,
  `parent_comment_id` bigint(20) NULL DEFAULT NULL,
  `admin_comment` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (9, '的', '1013558113@qq.com', '应该可以吧', '/images/avatar.png', '2021-04-02 15:40:36', 6, 8, b'0');
INSERT INTO `t_comment` VALUES (12, '人生海海', '2568635916@qq.com', '可以删除评论吗', '/images/avatar.png', '2021-04-02 15:53:39', 6, -1, b'0');

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blogaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blogname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `pictureaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_friend
-- ----------------------------

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `parent_message_id` bigint(20) NULL DEFAULT NULL,
  `admin_message` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_picture
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pictureaddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picturedescription` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picturename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picturetime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_picture
-- ----------------------------

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (3, '总结学习');
INSERT INTO `t_type` VALUES (34, 'Java');
INSERT INTO `t_type` VALUES (36, '浴室沉思');
INSERT INTO `t_type` VALUES (58, 'SpringBoot');
INSERT INTO `t_type` VALUES (59, 'MySQL');
INSERT INTO `t_type` VALUES (60, 'redis');
INSERT INTO `t_type` VALUES (61, '设计模式');
INSERT INTO `t_type` VALUES (62, '数据结构与算法');
INSERT INTO `t_type` VALUES (64, '项目');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'https://pic2.zhimg.com/80/v2-7f03cd2301fe14c49f8a0bf946b2de9d_1440w.jpg?source=1940ef5c', '2021-03-28 02:54:24', 'ljx282018@iCloud.com', 'LinJiXian', 'f6fca4499012133bd386f8c9c06311a0', NULL, '2021-03-28 02:55:05', 'linjx');
INSERT INTO `t_user` VALUES (3, NULL, '2021-03-28 15:21:59', 'test@163.com', 'tese', 'b59c67bf196a4758191e42f76670ceba', NULL, '2021-03-28 15:23:10', 'test');

SET FOREIGN_KEY_CHECKS = 1;
