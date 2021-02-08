# Introduction

AOE RMS Spoon is a utility that allows you to programmatically create RMS Ageof Empires random script map files.
To support this, it provides a small semantic analysis to help you avoid commons errors. Furthermore, it can actually target different age of empires version, albeit for now the only implementation ifs the latest, Definitive Edition.

# Build

To build:

```
cd aoe-rms-spoon
mvn build test install
```

You can then use the generateed artifact inside your Java project in order to build the rms file.

# Examples

In `src/test/java` there are several examples you can use to help gettijng started. I recommend the TeamArena, which tries to build an arean like map.

# Bug and feature requests

Please fill a github issue if you detct something weird or you want a new feature in the framework.

