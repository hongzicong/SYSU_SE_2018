# Test Report

**姓名：** 洪梓聪

**学号：** 15344015

---

## 测试目的

1. 测试默认构造函数是否成功构建

2. 测试以颜色为参数的构造函数是否成功构建

3. 测试 Jumper 是否在无障碍物情况下跳跃两格

4. 测试 Jumper 是否在前面一格有 Flower 的时候跳跃

5. 测试 Jumper 是否在前面一格有 Rock 的时候跳跃

6. 测试 Jumper 是否在前面一格有 Bug 的时候跳跃

## 测试原理

1. 构建 ActorWorld， 并设置 Jumper 的初始位置为 (LOCATION_ROW, LOCATION_COL) 和 初始方向为 Location.RIGHT

2. 在 (LOCATION_ROW, LOCATION_COL + 2) 的位置分别放置 Rock， Flower， Bug 还有不放任何东西，进行测试

3. 查看是否当不放任何东西， Rock 和 Flower 时能跳过，当 Bug 时不能跳过。

## 测试结果

各个样例成功运行