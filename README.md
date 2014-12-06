simple-code-generator
=====================

Simple code generator is a simple, but powerful tool for multiple text files generator. It can be useful for example to prepare CRUD operations on any web application. It uses Velocity templates and json file with parameters.

Usage
=====================
1. Prepare template directory:
  - it can contain subdirectories and your template files with `.vm` extension
  - it must contain `parameters.json` file with your parameters which will be used to fill your templates
  - you can use parameters also in directory names, e.g. `__myName__`, where `myName` is a parameter name
  - parameters in template files must be prefixed by `p.`, e.g. `${p.myName}`
2. Put your template direcotry in the same directory as your **simlple-code-generator** tool.
3. Run **simple-code-generator**:
  - `java -jar simple-code-generator.jar`
4. It generates code in directory named with `_result` suffix.

Example
=======
Check an `example` template directory.

Links
=====
- Velocity templates user guide
  - https://velocity.apache.org/engine/releases/velocity-1.5/user-guide.html
