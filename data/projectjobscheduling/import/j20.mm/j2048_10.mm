************************************************************************
file with basedata            : md368_.bas
initial value random generator: 1510671611
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  22
horizon                       :  160
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     20      0       28        7       28
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        3          3           5   8  11
   3        3          2           6   9
   4        3          1          16
   5        3          3           7  12  15
   6        3          1           7
   7        3          3          10  14  20
   8        3          3           9  13  17
   9        3          3          15  19  21
  10        3          2          13  16
  11        3          3          12  15  21
  12        3          3          17  18  20
  13        3          2          18  21
  14        3          1          17
  15        3          1          20
  16        3          1          18
  17        3          1          19
  18        3          1          19
  19        3          1          22
  20        3          1          22
  21        3          1          22
  22        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     5       7    5    7    7
         2     8       6    4    4    7
         3    10       6    2    3    6
  3      1     1       8    8    3    5
         2     6       4    6    3    3
         3    10       1    4    2    2
  4      1     3       7    4    3    6
         2     4       7    3    3    5
         3     5       6    1    2    5
  5      1     7       6    9    4    8
         2     7       6    6    4    9
         3     8       6    4    2    5
  6      1     4       7    8    4    4
         2     5       5    7    3    4
         3     7       5    6    3    4
  7      1     3      10    7   10    9
         2     6      10    6    8    7
         3    10      10    4    6    3
  8      1     4       7    4   10    5
         2     6       7    3   10    5
         3     8       6    2   10    5
  9      1     2       6   10    8    8
         2     4       6    6    4    5
         3     6       6    5    3    5
 10      1     3       4    6    3    8
         2     3       4    5    4    8
         3     8       3    4    3    7
 11      1     9       7    7    7    7
         2     9       7    8    5    9
         3    10       4    6    5    6
 12      1     2       9    4    6    9
         2     3       8    3    5    9
         3     9       6    3    3    8
 13      1     1       2    8    5    8
         2     4       2    7    4    7
         3     8       1    4    4    6
 14      1     6       8    6    8    5
         2     7       8    5    7    4
         3     8       7    4    5    3
 15      1     2       2    2    5    7
         2     3       1    2    3    6
         3     4       1    1    1    6
 16      1     2      10    3    9    6
         2     5       6    2    8    4
         3     9       5    2    5    1
 17      1     3      10    6    2    4
         2     4       8    3    2    4
         3     5       8    2    1    4
 18      1     3       9    8    5    7
         2     7       9    4    5    2
         3     7       9    6    4    1
 19      1     4       8    7    8    6
         2     7       6    6    8    5
         3     8       4    5    7    4
 20      1     5       4    9    7    9
         2     6       4    9    5    9
         3    10       3    9    4    9
 21      1     3       3    7    6    2
         2     4       3    5    5    1
         3    10       2    4    4    1
 22      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   25   30   99  112
************************************************************************
