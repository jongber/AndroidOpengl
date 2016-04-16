
attribute vec3 aColor;
attribute vec3 aPos;     //  attribute - > in, version es 300

varying vec3 vColor;

void main()
{
    gl_Position = vec4(aPos, 1);
    vColor = aColor;
}