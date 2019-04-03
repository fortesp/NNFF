# NNFF - Neural Network Feed Foward

Reads images with digits. Converts each pixel into RGB value to be fed into the Neural Network. 
The cells are trained and fired to recognize successfully a number with a specific target. 

Example of an output:

```
Reading images... Done.
Neural Network initialized.
Total INPUT cells: 1024
Total HIDDEN cells #1: 614
Total HIDDEN cells #2: 307
Total OUTPUT cells: 5

Epochs: 5
Batches: 5
Iterations: 250


Epoch #1
Learning... Done.
Firing...

Target: 1
Output: 
 (0.91352206) 91% likely to be a 1
 (0.08315082) 8% likely to be a 2
 (0.10422682) 10% likely to be a 3
 (0.1668892) 17% likely to be a 4
 (0.25973925) 26% likely to be a 5
PASSED.

Target: 2
Output: 
 (0.002700394) 0% likely to be a 1
 (0.67905897) 68% likely to be a 2
 (0.031628378) 3% likely to be a 3
 (0.02233936) 2% likely to be a 4
 (0.30469373) 30% likely to be a 5
PASSED.

Target: 3
Output: 
 (0.008017112) 1% likely to be a 1
 (0.114655584) 11% likely to be a 2
 (0.87434596) 87% likely to be a 3
 (0.063642256) 6% likely to be a 4
 (0.13389836) 13% likely to be a 5
PASSED.

Target: 3
Output: 
 (0.039496824) 4% likely to be a 1
 (0.88973904) 89% likely to be a 2
 (0.20663506) 21% likely to be a 3
 (0.060800858) 6% likely to be a 4
 (0.23931819) 24% likely to be a 5
FAILED.

Target: 4
Output: 
 (0.0035491886) 0% likely to be a 1
 (0.3064328) 31% likely to be a 2
 (0.07893467) 8% likely to be a 3
 (0.605019) 61% likely to be a 4
 (0.08867973) 9% likely to be a 5
PASSED.

Target: 5
Output: 
 (0.006214881) 1% likely to be a 1
 (0.07108693) 7% likely to be a 2
 (0.07451407) 7% likely to be a 3
 (0.3341005) 33% likely to be a 4
 (0.6131259) 61% likely to be a 5
PASSED.
```
