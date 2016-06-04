# Document

####MoriSupa 2016.6
========================================================

## 1.How to use it?
>This is quite easy to use. After installing it into your eclipse, try to find __littlemylynview__ in 
window>show view. It's time to try it out.

>1.There's a pull-down menu at the context toolbar. You can find 'New Task' there. Fill the information about the task in the pop up menu and click OK. You can then see
it in the viewer.

>2.Click on each task name and there'll be a popup dialog to let you change the state of the task.

>3.Click on the class name in each task's related class list and the related file will be opened in the editor.

>4.When a task is activated, the '.java' class you have modified, added or removed will all be considered as a related
class of the current activated task. Make sure you are working on the actual task.

##2.Architecture
>1.In a word, we adopt the __MVC__ view point to split the *modules* and the *workload*.

>2.Handlers and Actions are considered as __Controller__, while the simple entity class and the 
advanced data manger together with the node __family__ for TreeViewer are considered as __model__. And __View__ is 
actually about the MainView, those popup dialogs and other small widgets.

>3.For more detailed things, just feel free to see the structure of the project and you'll
almost know everything.

##3.Thoughts
>1.__No one__ needs to know __everything__ about the project, even the group leader.

>2.__Controller__ is actually a bridge between __View__ and __Model__, which means actually that
the three parts of __MVC__ are not of equivalent weight. However, to determine the interface of controllers is almost 
impossible ahead of time. Our solution is that the controller guy should work in pair with the view guy, which 
is probably the most efficient way we could figure out.

>3.The cost of learning new things is too high for a whole team. Thus __Rule of Dictatorship__ may be adopted. Some of the 
team members should work on what they're familiar with. However, I'm sorry for 'deprive'-ing them of the right to take in new nutritions.

>4.Eclipse is not very graceful in my own opinion.(And wjm agrees). 懐かしいな(Feel lost without)、アートム(Atom)と(&)サブァイム(Sublime)

That's all. Thanks for reading.
