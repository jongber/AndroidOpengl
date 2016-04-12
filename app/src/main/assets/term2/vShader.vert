
attribute vec3 pos;     //  attribute - > in, version es 300

varying vec3 outColor;  //  name must be same!! with fragment shader !!

void main()
{
    gl_Position = vec4(pos, 1);
    outColor = pos;
}