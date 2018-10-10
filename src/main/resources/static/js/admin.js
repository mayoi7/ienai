let baseinfoManage = {
  template: `<div>
    <table class="table">
      <thead>
        <tr>
          <th>总访问量</th>
          <th>总注册人数</th>
          <th>男女比例</th>
          <th>总便条数</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>132846</td>
          <td>5637</td>
          <td>63:37</td>
          <td>86325</td>
        </tr>
      </tbody>
    </table>
  </div>`
};

let websiteManage = {
  template: `<div class="visited-tmpl" style="overflow-y: auto">
  <table class="table table-hover">
    <thead>
      <tr>
        <th>访问用户</th>
        <th>ip</th>
        <th>访问时间</th>
        <th>标记</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>KurobaAkira</td>
        <td>192.128.0.1</td>
        <td>2018.09.23 21:27:25</td>
        <td>
          <button class="div-btn" style="color: #99989b;">
            <svg fill="currentColor" style="width: 17px; position: relative; top: 4px;" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
              <g><path d="M234.7,10h529.9v620.8H234.7V10z"/><path d="M765.3,988.6L500.4,628h264.9V988.6z"/><path d="M499.6,990"/><path d="M234.7,980.3V630.8h264.9L234.7,980.3z"/></g>
            </svg>
          </button>
        </td>
      </tr>
      <tr>
        <td>游客</td>
        <td>128.112.10.2</td>
        <td>2018.09.22 15:31:16</td>
        <td>
          <button class="div-btn" style="color: #99989b;">
            <svg fill="currentColor" style="width: 17px; position: relative; top: 4px;" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
              <g><path d="M234.7,10h529.9v620.8H234.7V10z"/><path d="M765.3,988.6L500.4,628h264.9V988.6z"/><path d="M499.6,990"/><path d="M234.7,980.3V630.8h264.9L234.7,980.3z"/></g>
            </svg>
          </button>
        </td>
      </tr>
      <tr class="marked">
        <td>Misaki177</td>
        <td>132.118.0.5</td>
        <td>2018.09.22 06:07:13</td>
        <td>
          <button class="div-btn" style="color: #99989b;">
            <svg fill="currentColor" style="width: 17px; position: relative; top: 4px;" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
              <g><path d="M234.7,10h529.9v620.8H234.7V10z"/><path d="M765.3,988.6L500.4,628h264.9V988.6z"/><path d="M499.6,990"/><path d="M234.7,980.3V630.8h264.9L234.7,980.3z"/></g>
            </svg>
          </button>
        </td>
      </tr>
      <tr>
        <td>kkyori</td>
        <td>112.173.25.37</td>
        <td>2018.09.20 23:20:56</td>
        <td>
          <button class="div-btn" style="color: #99989b;">
            <svg fill="currentColor" style="width: 17px; position: relative; top: 4px;" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
              <g><path d="M234.7,10h529.9v620.8H234.7V10z"/><path d="M765.3,988.6L500.4,628h264.9V988.6z"/><path d="M499.6,990"/><path d="M234.7,980.3V630.8h264.9L234.7,980.3z"/></g>
            </svg>
          </button>
        </td>
      </tr>
    </tbody>
  </table>
  </div>`
};

let userManage = {
  template: `<div class="user-tmpl">
    <div class="search">
      <input type="text" class="search-input easy-input" placeholder="输入要查询用户的用户名"/>
      <button class="div-btn search-btn">
        <svg fill="currentColor" style="width: 21px;" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
          <g><g><path d="M377.5,130.4c135.1,0,245,110.5,245,246.4c0,51.2-15.7,100.4-45.4,142.4L553,553.3l-33.9,24.2c-41.7,29.9-90.7,45.7-141.6,45.7c-135.1,0-245-110.5-245-246.4C132.5,240.9,242.4,130.4,377.5,130.4L377.5,130.4z M377.5,7.2C174.5,7.2,10,172.7,10,376.8s164.5,369.6,367.5,369.6c79.3,0,152.6-25.5,212.6-68.5l295.1,296.8c12,12.1,27.7,18.1,43.4,18.1s31.4-6,43.4-18.1c24-24.1,24-63.2,0-87.3L676.9,590.6c42.7-60.4,68.1-134.1,68.1-213.8C745,172.7,580.5,7.2,377.5,7.2L377.5,7.2L377.5,7.2z"/></g></g>
        </svg>
      </button>
    </div>
    <div class="user-info center-block">
      <div class="info col-md-10" style="height: 100%;">
        <img src="H:/UI TEST/IENAI/img/default-avatar.png" class="avatar img-thumbnail" alt="用户头像"/>
        <div class="sex info-item" style="right: 80px; top: 110px;">
          <span class="title">性别</span><br/>
          <span class="desp" id="user_sex">男</span>
        </div>
        <div class="name info-item" style="right: 135px; top: 165px;">
          <span class="title">真名</span></br>
          <span class="desp">刘*匀</span>
        </div>

        <div class="userid info-item" style="right: 86px; bottom: 20px;">
          <span class="title">用户id：</span>
          <span class="desp">75d4-dw5d-sdw2-51sq</span>
        </div>
        <div class="username info-item" style="left: 139px; bottom: 60px;">
          <span class="title">用户名：</span>
          <span class="desp">KurobaAkira</span>
        </div>
        <div class="signup-time info-item" style="right: 28px; bottom: 16px;">
          <span class="title">注册时间：</span>
          <span class="desp">2018-09-21 15:12</span>
        </div>
        <div class="login-time info-item" style="left: 138px; bottom: 28px;">
          <span class="title">上次登陆：</span>
          <span class="desp">2018-09-24 17:40</span>
        </div>
        <div class="invcode info-item" style="right: 77px; top: 19px;">
          <span class="title">邀请码：</span>
          <span class="desp">sfwr-4dwa-7f2s</span>
        </div>

        <div class="invcode info-item" style="bottom: 97px;">
          <span class="title">用户身份</span><br/>
          <kbd class="desp admin">管理员</kbd>
        </div>
        <div class="invcode info-item" style="right: 80px; bottom: 33px;">
          <span class="title">用户状态</span><br/>
          <kbd class="desp ok">正常</kbd>
        </div>
      </div>
      <div class="operate col-md-2" style="height: 100%; border-left: 2px solid #00000045;">
        <div>操作列表</div>
        <ul class="list-unstyled">
          <li class="level-up" style="margin-top: 27px;"><button class="div-btn">授权</button></li>
          <li class="reset"><button class="div-btn">重置</button></li>
          <li class="ban"><button class="div-btn">封禁</button></li>
          <li class="delete"><button class="div-btn">删除</button></li>
        </ul>
      </div>
    </div>
  </div>`
};

let noteManage = {
  template: `<div class="note-tmpl">
    <table id="note_table" class="table table-hover">
      <thead>
        <tr>
          <th>#</th>
          <th>用户名</th>
          <th>标题</th>
          <th>概要</th>
          <th>寄给用户</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Masdi</td>
          <td>title1</td>
          <td>Lorem ipsum dolor sit amet, consectetur ...</td>
          <td>send_to</td>
          <td>
            <button class="lock div-btn" title="锁定" style="">
              <svg style="width: 20px" fill="currentColor" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
                <g><path d="M372,402.2H241V249.3c0-33.3,6.6-64.3,19.8-93.1c13.2-28.8,31.4-54.1,54.6-75.9s50.6-39,82.2-51.5C429.1,16.2,463.6,10,501,10c33.3,0,65.4,6.2,96.2,18.7c30.9,12.5,58.3,29.6,82.2,51.5c23.9,21.8,43,47.2,57.2,75.9C750.9,185,758,216,758,249.3v152.9H632.1V278.4c0-43.7-12.1-77.5-36.4-101.4c-24.3-23.9-57.2-35.9-98.8-35.9c-37.5,0-67.6,12-90.5,35.9c-22.9,23.9-34.3,57.7-34.3,101.4V402.2L372,402.2z M820.4,464.6c18.7,0,34.9,6.8,48.4,20.3c13.5,13.5,20.3,29.6,20.3,48.4v322.5c0,18.7-3.3,36.2-9.9,52.5c-6.6,16.3-15.6,30.5-27,42.7c-11.4,12.1-25,21.7-40.6,28.6c-15.6,6.9-32.8,10.4-51.5,10.4H234.7c-18.7,0-35.7-3.5-51-10.4c-15.3-6.9-28.3-16.1-39-27.6c-10.8-11.4-19.1-24.8-25-40.1c-5.9-15.3-8.8-31.6-8.8-48.9V533.3c0-18.7,6.6-34.9,19.8-48.4c13.2-13.5,29.1-20.3,47.9-20.3H241H372h260.1H758H820.4L820.4,464.6z"/></g>
              </svg>
            </button>
            <button class="delete div-btn" title="删除" style="">
              <svg style="width: 20px;" fill="currentColor" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
                <g><path d="M386.4,824.5c26,0,45.4-19.5,45.4-45.4V376.7c0-26-19.5-45.4-45.4-45.4c-19.5,6.5-38.9,26-38.9,51.9v402.4C347.5,805,367,824.5,386.4,824.5z"/><path d="M821.3,337.7c-26,0-45.4,19.5-45.4,45.4v480.3c0,26-19.5,38.9-38.9,38.9H256.6c-26,0-38.9-19.5-38.9-38.9V383.2c0-26-19.5-45.4-45.4-45.4c-26,0-38.9,19.5-38.9,45.4v525.7c0,45.4,38.9,77.9,77.9,77.9h571.1c45.4,0,77.9-38.9,77.9-77.9V383.2C860.2,357.2,840.7,337.7,821.3,337.7z"/><path d="M938.1,175.5H775.8V91.1c0-45.4-38.9-77.9-77.9-77.9H295.6c-45.4,0-77.9,38.9-77.9,77.9v84.4H55.4c-26,0-45.4,19.5-45.4,45.4s19.5,45.4,45.4,45.4h889.1c26,0,45.4-19.5,45.4-45.4C983.5,195,964,175.5,938.1,175.5L938.1,175.5z M697.9,175.5H302.1V143c0-26,19.5-38.9,38.9-38.9h311.5c26,0,38.9,19.5,38.9,38.9v32.5H697.9z"/><path d="M607.1,824.5c26,0,45.4-19.5,45.4-45.4V376.7c0-26-19.5-45.4-45.4-45.4c-26,6.5-45.4,26-45.4,51.9v402.4C561.7,805,581.1,824.5,607.1,824.5L607.1,824.5z"/></g>
              </svg>
            </button>
          </td>
        </tr>
        <tr>
          <td>2</td>
          <td>Asdaw</td>
          <td>title2</td>
          <td>Lorem</td>
          <td>send_to2</td>
          <td>
            <button class="lock locked div-btn" title="锁定" style="">
              <svg style="width: 20px" fill="currentColor" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
                <g><path d="M372,402.2H241V249.3c0-33.3,6.6-64.3,19.8-93.1c13.2-28.8,31.4-54.1,54.6-75.9s50.6-39,82.2-51.5C429.1,16.2,463.6,10,501,10c33.3,0,65.4,6.2,96.2,18.7c30.9,12.5,58.3,29.6,82.2,51.5c23.9,21.8,43,47.2,57.2,75.9C750.9,185,758,216,758,249.3v152.9H632.1V278.4c0-43.7-12.1-77.5-36.4-101.4c-24.3-23.9-57.2-35.9-98.8-35.9c-37.5,0-67.6,12-90.5,35.9c-22.9,23.9-34.3,57.7-34.3,101.4V402.2L372,402.2z M820.4,464.6c18.7,0,34.9,6.8,48.4,20.3c13.5,13.5,20.3,29.6,20.3,48.4v322.5c0,18.7-3.3,36.2-9.9,52.5c-6.6,16.3-15.6,30.5-27,42.7c-11.4,12.1-25,21.7-40.6,28.6c-15.6,6.9-32.8,10.4-51.5,10.4H234.7c-18.7,0-35.7-3.5-51-10.4c-15.3-6.9-28.3-16.1-39-27.6c-10.8-11.4-19.1-24.8-25-40.1c-5.9-15.3-8.8-31.6-8.8-48.9V533.3c0-18.7,6.6-34.9,19.8-48.4c13.2-13.5,29.1-20.3,47.9-20.3H241H372h260.1H758H820.4L820.4,464.6z"/></g>
              </svg>
            </button>
            <button class="delete div-btn" title="删除" style="">
              <svg style="width: 20px;" fill="currentColor" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
                <g><path d="M386.4,824.5c26,0,45.4-19.5,45.4-45.4V376.7c0-26-19.5-45.4-45.4-45.4c-19.5,6.5-38.9,26-38.9,51.9v402.4C347.5,805,367,824.5,386.4,824.5z"/><path d="M821.3,337.7c-26,0-45.4,19.5-45.4,45.4v480.3c0,26-19.5,38.9-38.9,38.9H256.6c-26,0-38.9-19.5-38.9-38.9V383.2c0-26-19.5-45.4-45.4-45.4c-26,0-38.9,19.5-38.9,45.4v525.7c0,45.4,38.9,77.9,77.9,77.9h571.1c45.4,0,77.9-38.9,77.9-77.9V383.2C860.2,357.2,840.7,337.7,821.3,337.7z"/><path d="M938.1,175.5H775.8V91.1c0-45.4-38.9-77.9-77.9-77.9H295.6c-45.4,0-77.9,38.9-77.9,77.9v84.4H55.4c-26,0-45.4,19.5-45.4,45.4s19.5,45.4,45.4,45.4h889.1c26,0,45.4-19.5,45.4-45.4C983.5,195,964,175.5,938.1,175.5L938.1,175.5z M697.9,175.5H302.1V143c0-26,19.5-38.9,38.9-38.9h311.5c26,0,38.9,19.5,38.9,38.9v32.5H697.9z"/><path d="M607.1,824.5c26,0,45.4-19.5,45.4-45.4V376.7c0-26-19.5-45.4-45.4-45.4c-26,6.5-45.4,26-45.4,51.9v402.4C561.7,805,581.1,824.5,607.1,824.5L607.1,824.5z"/></g>
              </svg>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>`
};

let invcodeManage = {
  template: `<div class="invcode-tmpl">
    <div class="invcode-operate">
      <button class="addcode-btn div-btn" style="margin-bottom: 16px;">
        <kbd>添加一个邀请码</kbd>
      </button>
      <button id="sa" class="switch-btn div-btn" style="margin-left: 170px">显示全部</button>
      <button id="su" class="switch-btn div-btn">仅显示可用</button>
    </div>
    <table id="invcode_table" class="table table-hover">
      <thead>
        <tr>
          <th style="width: 237px">邀请码</th>
          <th style="width: 140px">使用情况</th>
          <th style="width: 207px">使用人</th>
          <th style="width: 305px">使用时间</th>
        </tr>
      </thead>
      <tbody>
        <tr class="unused new-add">
          <td>fdss-sd45-dwr8</td>
          <td><kbd>未使用</kbd></td>
          <td>/</td>
          <td>/</td>
        </tr>
        <tr class="used">
          <td>sd8s-h1yu-iy4t</td>
          <td><kbd>已使用</kbd></td>
          <td>Kuroba_Akira</td>
          <td>2018/09/25 17:15:32</td>
        </tr>
      </tbody>
    </table>
  </div>`
};

let routes = [
  {
    path: '/',
    component: baseinfoManage
  },
  {
    path: '/website',
    component: websiteManage
  },
  {
    path: '/user',
    component: userManage
  },
  {
    path: '/note',
    component: noteManage
  },
  {
    path: '/invcode',
    component: invcodeManage
  },
];

let router = new VueRouter({
  routes
});

new Vue({
  el: '#app',
  router
});
