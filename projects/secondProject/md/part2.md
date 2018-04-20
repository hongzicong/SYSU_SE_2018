1. The *sideLength* is the number of steps that a BoxBug moves on each side of the rectangle.

```
// @file: boxBug/BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
else
{
    turn();
    turn();
    steps = 0;
}
```

2. The *steps* is a counter to count how many steps a BoxBug has moved one each side of the rectangle.

```
// @file: boxBug/BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
else
{
    turn();
    turn();
    steps = 0;
}
```

3. One *turn* method turn right 45 degree and two *turn* method turn right 90 degree. If the BoxBug has moved *sideLength* steps, it has to turn right 90 degree.

```
// @file: boxBug/BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
else
{
    turn();
    turn();
    steps = 0;
}
```

4. Because the BoxBug is a subclass of Bug, which means BoxBug extends Bug, and Bug has a public method *move*, BoxBug also has *move* method.

```
// @file: boxBug/BoxBug.java
// @line: 25
public class BoxBug extends Bug

// @file: info/gridworld/actor/Bug
// @line: 71
public void move()
```

5. Yes, because there is no setter method for *sideLength* and *sideLength* is a private field.

```
// @file: boxBug/BoxBug.java
// @line: 28
private int sideLength;
```

6. Yes, if the BoxBug can't move because there is another actor like Rock and Bug in front of it or a bound, it needs to call *turn* method for a new path.

```
// @file: boxBug/BoxBug.java
// @line: 45~55
if (steps < sideLength && canMove())
{
    move();
    steps++;
}
else
{
    turn();
    turn();
    steps = 0;
}
```

7. The moment that the BoxBug is initialized or BoxBug turn to a new direction.

```
// @file: boxBug/BoxBug.java
// @line: 36
steps = 0;

// @file: boxBug/BoxBug.java
// @line: 51~55
else
{
    turn();
    turn();
    steps = 0;
}
```