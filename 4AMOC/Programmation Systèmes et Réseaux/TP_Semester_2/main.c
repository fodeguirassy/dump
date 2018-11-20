#include <stdio.h>
#include <pthread.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h>

pthread_cond_t condition = PTHREAD_COND_INITIALIZER;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

static void *taskHandle(void *t_data){
        printf("Hello world from taskHandle\n");

        sleep(5);
        pthread_mutex_lock(&mutex);
        pthread_cond_signal(&condition);
        pthread_mutex_unlock(&mutex);

        (void) t_data;
        pthread_exit(NULL);
}

static void *waitTask(void *data){
    while(1){
        pthread_mutex_lock(&mutex);
        pthread_cond_wait(&condition, &mutex);
        puts("counter's reached 5 seconds.");
        pthread_mutex_unlock(&mutex);
    }

    (void) data;
    pthread_exit(NULL);
}

int main() {

    printf("Hello world\n");
    puts("init main");

   clock_t begin = clock();
   double time_spent;

    pthread_t thread, wait_thread;
    if(pthread_create(&thread, NULL,taskHandle, NULL)== -1){
        perror("Error creating Thread");
        return EXIT_FAILURE;
    }
    if(pthread_create(&wait_thread, NULL, waitTask, NULL)== -1){
        perror("Error creating waiting thread");
        return EXIT_FAILURE;
    }

    if(pthread_join(thread, NULL)){
        perror("Error join");
        return EXIT_FAILURE;
    }
    
    puts("end main");
    return 0;
}