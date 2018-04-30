# Design Report

**姓名：** 洪梓聪

**学号：** 15344015

---

a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

If the location two cells in front contains a flower, the jumper may jump to the cell and remove the flower. But if there is a rock in the cell, the jumper may turn right for a new direction.

b. What will a jumper do if the location two cells in front of the jumper is out of the grid?

It will turn right for a new direction.

c. What will a jumper do if it is facing an edge of the grid?

It will turn right for a new direction.

d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

It will turn right for a new direction.

e. What will a jumper do if it encounters another jumper in its path?

If the jumper is in the position in front of or two cell in front of it, it may turn its direction. Otherwise, it will continue to jump two cells.

f. Are there any other tests the jumper needs to make?

Yes, as follows:
1. Whether the jumper can jump over the bug or other actors or not.
2. What will the jumper do if the jumper is wrapped by other actors.