import request from '@/utils/request'
const api_name = '/admin/cmn/dict'

export default {
  // 数据字典列表
  dictList(id) {
    return request({
      url: `${api_name}/children/${id}`,
      method: 'get'
    })
  },
  export() {
    return request({
      url: `${api_name}/export`,
      method: 'get'
    })
  }
}
