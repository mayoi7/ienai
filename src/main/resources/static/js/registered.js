// 点击注册按钮，校验输入
function verifyInput(event) {
  $('.input-text').blur();
  if($('.input-text').hasClass('wrong-input')) {  // 说明输入出错
    return false;
  } else {
    return true;
  }
}

// 显示注册的下一步
function showNextSignUp() {
  $('.black-drop').show();
  $('#form1 .input-lover').fadeIn(220);
}

// black-drop点击后消失的规则
function hideBlackDropRule() {
  if($('.input-lover').is(':visible')) {  // 假如input-lover可见
    $('.black-drop').hide();
    $('.input-lover').hide();
  }
}

// 修改placeholder
function changePlaceHolder(selector, toChange) {
  $(selector).attr('placeholder', toChange);
}

// 提交最终表单
function submitForm() {
  $('#form1').submit();
}
