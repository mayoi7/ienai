Vue.component('note-item', {
  props: ['note'],
  template:  `
    <li class="item">
      <div class="back-msg note-msg">
        {{note.title}}
      </div>
      <div class="note-action" :value="note.id">
        <button type="button" class="like">
          <span class="like-pic" style="display: inline-flex; align-items: center;">
          ​ <svg fill="currentColor" viewBox="0 0 24 24" width="1.2em" height="1.2em"><path d="M2 8.437C2 5.505 4.294 3.094 7.207 3 9.243 3 11.092 4.19 12 6c.823-1.758 2.649-3 4.651-3C19.545 3 22 5.507 22 8.432 22 16.24 13.842 21 12 21 10.158 21 2 16.24 2 8.437z" fill-rule="evenodd"></path></svg>
          </span>
          <span class="like-count">{{note.like}}</span>
        </button>
        <button type="button" class="interest">
          <span class="interest-pic" style="display: inline-flex; align-items: center;">
          ​ <svg fill="currentColor" viewBox="0 0 24 24" width="1.2em" height="1.2em"><path d="M2 8.437C2 5.505 4.294 3.094 7.207 3 9.243 3 11.092 4.19 12 6c.823-1.758 2.649-3 4.651-3C19.545 3 22 5.507 22 8.432 22 16.24 13.842 21 12 21 10.158 21 2 16.24 2 8.437z" fill-rule="evenodd"></path></svg>
          </span>
          <span class="interest-count">{{note.interest}}</span>
        </button>
      </div>
      <div class="main-msg note-msg" style="display: none;">{{note.general}}</div>
      <div class="goback" style="display: none;">
        <svg fill="currentColor" style="width: 19px;" version="1.1" x="0px" y="0px" viewBox="0 0 1000 1000" enable-background="new 0 0 1000 1000" xml:space="preserve">
          <g><path d="M486.7,203.9L487.9,10L93.6,336l390.1,330.2l1.2-185.2c389.6,52.8,221.3,287-0.7,509C1253.3,488.3,795.8,257.1,486.7,203.9"/></g>
        </svg>
      </div>
      <div class="detail-msg" style="display: none;">
        <div class="write-to">
          {{note.title}}
        </div>
        <div class="write-msg">
          {{note.complete}}
        </div>
        <div class="write-from">
          ——— 来自{{note.from}}
        </div>
      </div>
    </li>
  `
});

var $app = new Vue({
  el: '#app',
  data: function() {
	// order为0表示按时间排序，1表示按点赞数排序，2表示只看写给自己的
	var raw_data = {user: null, notes: null, search: {
		page: [1,2,3,4,5], 
		order: 0,
		current_page: 1,
		self_only: 0
	}};
	  
    // TODO: 改为从服务器通过ajax获取数据
	$.get("/note/returnUserAndNote", (data)=>{
		if(null == data) alert("异常");
		else {
			raw_data.user = data.user;
			raw_data.notes = data.noteList;
			
			/* 以下是addLikeAndInterest方法，但是因为数据源不一样所以单独把函数体抽出来写 */
			  let noteIdList = [];
			  let rawList = raw_data.notes;
			  /* 按顺序把当前页面note的id放入list中  */
			  for(let i=0; i<rawList.length; ++i) {
				  noteIdList.push(rawList[i].id);
			  }
			  
			  $.ajax({
				  url: "/note/returnUserLikeAndInterest",
				  type: "POST",
				  data: {
					  "idList": noteIdList
				  },
				  dataType:"json",	// 这条必须加
				  traditional: true,
				  success: function(data) {
					  for(let i=0; i<data.length; ++i) {
						  let $li = $('.note-list li').eq(i);
						  if(data[i].liked == true) {
							  $li.find('.like-pic').addClass('liked');
						  } else {
							  $li.find('.like-pic').removeClass('liked');
						  }
						  
						  if(data[i].cared == true) {
							  $li.find('.interest-pic').addClass('interested');
						  } else {
							  $li.find('.interest-pic').removeClass('interested');
						  }
					  }
				  }
			});
		}
	});
	
	return raw_data;
  },
  methods: {
	  turnPage: function(pageNum) {
		  $.get("/note/returnPageInTimeOrder?pageNum=" + this.search.page[pageNum], (data)=> {
			  // 渲染页面
			  this.notes = data;
			  // 更新数据
			  this.search.current_page = pageNum;
			  
			  if(data.length >= 15 && pageNum == 4) {	// 说明没有到尾页，所以该跳页了（这里4就是最后一个，因为从0开始）
				  let fp = this.search.page[0];
				  this.search.page = [fp+1, fp+2, fp+3, fp+4, fp+5];
			  }	  
			  // 添加current-page样式
			  $('.page-list li').removeClass("current-page");
			  $('.page-list li:eq' + '(' + pageNum + ')').addClass("current-page");
			  
			  addLikeAndInterest();
		  });
	  },
	  orderByTime: function() {
		  $.get("/note/returnPageInTimeOrder?pageNum=" + this.search.current_page, (data)=> {
			  // 渲染页面
			  this.notes = data;
			  // 因为是刷新当前页，所以不用考虑跳页的问题
			  
			  addLikeAndInterest();
		  });
	  },
	  orderByHot: function() {	// hot指的是点赞数
		  $.get("/note/returnPageInLikeOrder?pageNum=" + this.search.current_page, (data)=> {
			  // 渲染页面
			  this.notes = data;
			  // 因为是刷新当前页，所以不用考虑跳页的问题
			  
			  addLikeAndInterest();
		  });
	  },
	  orderBySendMe: function() {
		  $.get("/note/returnPageSendMeInTimeOrder?pageNum=" + this.search.current_page, (data)=> {
			  // 渲染页面
			  this.notes = data;
			  // 因为是刷新当前页，所以不用考虑跳页的问题
			  
			  addLikeAndInterest();
		  });
	  },
	  orderByFromMe: function() {
		  $.get("/note/returnPageFromMeInTimeOrder?pageNum=" + this.search.current_page, (data)=> {
			  // 渲染页面
			  this.notes = data;
			  // 因为是刷新当前页，所以不用考虑跳页的问题
			  
			  addLikeAndInterest();
		  });
	  }
  }
});