//para compilar execute: gcc -lGL -lGLU -lglut teste.c -o teste

#include <GL/gl.h>
#include <GL/glut.h>

//Drawing funciton
void draw(void)
{
  //Background color
  glClearColor(1,1,1,1);
  glClear(GL_COLOR_BUFFER_BIT );


	// projecao do plano cartesiano
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();

	// limitar o plano cartesiano
	gluOrtho2D(-3, 3, -3, 3); 
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();


//primeiro triangulo
	glBegin(GL_TRIANGLES);
	
	glColor3f(0,1,0);

	glVertex2f(0, 0);	
	glVertex2f(1, 1);
	glVertex2f(2, 0);

	glEnd();

//segundo triangulo

	glBegin(GL_TRIANGLES);
	
	glColor3f(0,0,1);

	glVertex2f(0, 0);	
	glVertex2f(-1, 1);
	glVertex2f(-2, 0);

	//glColor3f(0,0,1);

	//glVertex2f(-2, -2);

	glEnd();
	
	//*/


  //Draw order
  glFlush();
}

//Main program
int main(int argc, char **argv)
{
  glutInit(&argc, argv);
  //Simple buffer
  glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB );
  glutInitWindowPosition(50,25);
  glutInitWindowSize(500,500);
  glutCreateWindow("t3");

  //Call to the drawing function
  glutDisplayFunc(draw);
  glutMainLoop();

  return 0;
}

