
precision mediump float;

varying vec3 outColor;   //  name must be same!! with vertex shader !!

void main()
{
    gl_FragColor = vec4(outColor, 1);
}