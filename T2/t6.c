#include <GL/glut.h>
#include <stdlib.h>


void init(void){
	glClearColor(9.0,9.0,9.0,0.0);
	glShadeModel (GL_FLAT);
}
void display(void){
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(0.0,0.0,0.6);

	glutWireSphere(2.0,30,36); //raio, fatias, stacks
//	glutSolidSphere(2.0,30,36); //raio, fatias, stacks
		
//	gluSphere(gluNewQuadric(),2.0,30,36); //raio, fatias "verticais", fatias "horizontais"
	glutSwapBuffers();
}
void reshape(int w, int h){
	glMatrixMode(GL_PROJECTION);
	gluPerspective(50.0,w/(GLfloat)h, 3.0,90.0);
	glMatrixMode(GL_MODELVIEW);
	gluLookAt(2.0,4.0,5.0,0.0,0.0,0.0,0.0,1.0,0.0);//posição do observador
}
int main(int argc, char** argv){
	glutInit(&argc, argv);
 	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutInitWindowSize(800,600);
	glutInitWindowPosition(10,10);
 	glutCreateWindow("SimpleSphere");
	init();
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutMainLoop();
	return 0;
	


}
