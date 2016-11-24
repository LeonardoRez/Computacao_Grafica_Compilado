#include <math.h>
#include <GL/glut.h>

void display();
void specialKeys();

void desenha(){
 
  //  Limpa a tela e o Z-Buffer
  glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

  //Transformações
   glRotatef( -20, 1.0, 0.0, 0.0 );    // y
   glRotatef( 20, 0.0, 1.0, 0.0 );    // x
 
  // Outras Transformações
  // glScalef( 2.0, 2.0, 0.0 );          // Não está incluído
 
  //Lado amarelo - FRENTE
 glBegin(GL_POLYGON);
 
 	glColor3f( 1.0, 1.0, 0.0 );     
	glVertex3f(  0.5, -0.5, -0.5 );     
	glVertex3f(  0.5,  0.5, -0.5 );    
	glVertex3f( -0.5,  0.5, -0.5 ); 
	glVertex3f( -0.5, -0.5, -0.5 );
 
  glEnd();
 
  // Lado branco - TRASEIRA
 glBegin(GL_POLYGON);
  glColor3f(   1.0,  1.0, 1.0 );
  glVertex3f(  0.5, -0.5, 0.5 );
  glVertex3f(  0.5,  0.5, 0.5 );
  glVertex3f( -0.5,  0.5, 0.5 );
  glVertex3f( -0.5, -0.5, 0.5 );
  glEnd();
 
  // Lado laranja - DIREITA
 glBegin(GL_POLYGON);
  glColor3f(  1.0,  0.5,  0.0 );
  glVertex3f( 0.5, -0.5, -0.5 );
  glVertex3f( 0.5,  0.5, -0.5 );
  glVertex3f( 0.5,  0.5,  0.5 );
  glVertex3f( 0.5, -0.5,  0.5 );
  glEnd();
 
  // Lado verde - ESQUERDA
 glBegin(GL_POLYGON);
  glColor3f(   0.0,  1.0,  0.0 );
  glVertex3f( -0.5, -0.5, 0.5 );
  glVertex3f( -0.5,  0.5,  0.5 );
  glVertex3f( -0.5,  0.5, -0.5 );
  glVertex3f( -0.5, -0.5, -0.5 );
  glEnd();
 
  // Lado azul - TOPO
 glBegin(GL_POLYGON);
  glColor3f(   0.0,  0.0,  1.0 );
  glVertex3f(  0.5,  0.5,  0.5 );
  glVertex3f(  0.5,  0.5, -0.5 );
  glVertex3f( -0.5,  0.5, -0.5 );
  glVertex3f( -0.5,  0.5,  0.5 );
  glEnd();
 
  // Lado vermelho - BASE
 glBegin(GL_POLYGON);
  glColor3f(   1.0,  0.0,  0.0 );
  glVertex3f(  0.5, -0.5, -0.5 );
  glVertex3f(  0.5, -0.5,  0.5 );
  glVertex3f( -0.5, -0.5, 0.5 );
  glVertex3f( -0.5, -0.5, -0.5 );
  glEnd();
 
  glFlush();
  glutSwapBuffers();
 
}
int main(int argc, char* argv[]){
 
  //  Inicializa o GLUT e processa os parâmetros do usuário GLUT
  glutInit(&argc,argv);
 
  //  Requisita uma janela com buffer duplo e true color com um Z-buffer
  glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
 
  // Cria a janela do programa
  glutCreateWindow("t5");
 
  //  Habilita o teste de profundidade do Z-buffer
  glEnable(GL_DEPTH_TEST);
 
  // Funções
  glutDisplayFunc(desenha);
 
  //  Passa o controle dos eventos para o GLUT
  glutMainLoop();
 
  //  Retorna para o SO
  return 0;
 
}
