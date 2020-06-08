#include <stdlib.h>
#include <string.h>

/*
  This represents a process to be scheduled.
*/

typedef struct Process process;
struct Process {
  char* name;
  unsigned int arrival; // time at which this process arrives
  unsigned int time;    // time it takes to complete this process
  unsigned int remaining; // remaining time to complete this process
  unsigned int priority; // priority: higher value means "less important"
};

