# SM4文件加解密工具

该工具使用SM4加密算法提供了文件的加密和解密功能。它使用命令行界面，允许用户指定加密或解密操作，同时需要提供一个密钥、一个初始化向量（IV）、输入文件和输出文件。

## 功能

- 支持文件的加密和解密。
- 使用SM4加密算法，结合CBC模式和适当的填充。
- 命令行界面，易于使用。

## 如何使用

1. 编译Java代码。
2. 运行编译后的程序，提供必要的命令行参数。

## 使用方法

使用以下命令格式：

```
java Main <encrypt|decrypt> <key> <iv> <inputFile> <outputFile>
```

- `<encrypt|decrypt>`: 指定操作模式。使用`encrypt`进行加密，使用`decrypt`进行解密。
- `<key>`: 加密或解密所需的密钥。
- `<iv>`: 加密或解密所需的初始化向量（IV）。
- `<inputFile>`: 要加密或解密的文件。
- `<outputFile>`: 加密或解密后的输出文件。

### 示例

加密文件：

```
java Main encrypt 1234567890abcdef 1234567890abcdef inputFile.txt encryptedFile.txt
```

解密文件：

```
java Main decrypt 1234567890abcdef 1234567890abcdef encryptedFile.txt decryptedFile.txt
```

## 开发环境

- Java版本：建议使用Java 8或更高版本。
- 依赖：需要[Bouncy Castle](https://www.bouncycastle.org/java.html)库支持SM4算法。

## 贡献

如果您想为这个项目贡献代码或想法，请随时提交pull requests或开issue。

