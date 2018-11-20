#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

int main(){

        char* filePath = "sample.pdf";

        int fileDescriptor = open(filePath, O_RDWR | O_CREAT,0666);

        printf("%d\n", fileDescriptor);
        lseek(fileDescriptor, 0, SEEK_SET);
        while(lseek(fileDescriptor, +1, SEEK_CUR) <= lseek(fileDescriptor, -1, SEEK_END)){
                char tmp[1];
                //lseek(fileDescriptor, +1, SEEK_SET);
                read(fileDescriptor, tmp, sizeof(char));
                printf("%s\n", tmp);
                lseek(fileDescriptor, 0, SEEK_CUR);
        }
        return 0;
}
