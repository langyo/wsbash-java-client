# WebSocket Bash

## (Java Client)

[中文](README.md) | [English](README_eng.md)

这是一个基于 WebSocket 协议的通讯工具库，皆在让开发者能够方便、快速地完成客户端与服务端之间的通信部分，只需关注具体的数据处理逻辑。此工具库允许以多种语言编写，不同语言凭此库编写的客户端/服务端之间可以直接对接。

其他仓库：

|        语言         |  类型  |                           仓库位置                           |
| :-----------------: | :----: | :----------------------------------------------------------: |
|  JavaScript(HTML5)  | 客户端 | [wsbash-h5-client](https://github.com/mcbbs-developer/wsbash-h5-client) |
| JavaScript(Node.js) | 服务端 | [wsbash-node-server](https://github.com/mcbbs-developer/wsbash-node-server) |

## 概念

客户端与服务端之间的通讯依赖于 WebSocket 协议提供的文本流通讯。如同命令行一样，客户端在与服务端建立通讯后，可以通过互相发送类似命令行的文本互相调用各自注册好的“指令”。

它们之间会发送类似这样的文本：

```shell
# 由客户端发送，在 WebSocket 连接成功后首先需要注册。
execute system register h5

# 由服务端回复，提示注册成功，并提供此客户端建立的连接的编号（类似 session ID）。
data system register ok jCsi1m9z

# 由客户端发送，模拟请求一些数据；
# 其中，database get 是由库使用者自行注册的指令，langyo money 是传递进此指令所注册的函数的参数。
execute database get langyo money

# 由服务端回复，模拟触发此指令对应的函数，读取完数据库，并回复一个数字；
# 回复时，它会自动补齐发送的指令类型与执行状态(success / fail)，便于客户端正确识别。
data database get success no-money
```