/**
 * Created by MACHENIKE on 2017/8/14.
 */
angular.module('myApp')
// 公司行业
    .constant('types',[
        {id:0,name:'首页banner'},
        {id:1,name:'找职位banner'},
        {id:2,name:'找精英banner'},
        {id:3,name:'行业大图'}
    ])

    .constant('industries',[
        {id:0,name:'移动互联网'},
        {id:1,name:'电子商务'},
        {id:2,name:'企业服务'},
        {id:3,name:'O2O'},
        {id:4,name:'教育'},
        {id:5,name:'金融'},
        {id:6,name:'游戏'}
    ])

    .constant('Status',[
    {id:1,name:'草稿'},
    {id:2,name:'上线'}
    ])

    .constant('StatusOP',[
    {id:1,name:'上线'},
    {id:2,name:'下线'}
    ]);




