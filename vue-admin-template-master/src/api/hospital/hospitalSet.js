import request from '@/utils/request'

const api_name = '/admin/hospital/hospitalSet'

export default {
  // 分页条件查询
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'post',
      data: searchObj
    })
  },
  // 删除
  delete(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'delete'
    })
  },

  // 批量删除
  batchDelete(idList) {
    return request({
      url: `${api_name}/batch`,
      method: 'delete',
      data: idList
    })
  },

  // 锁定和取消锁定
  lock(id, status) {
    return request({
      url: `${api_name}/lock/${id}/${status}`,
      method: 'put'
    })
  },

  // 新增
  insert(record) {
    return request({
      url: `${api_name}`,
      method: 'post',
      data: record
    })
  },

  get(id) {
    return request({
      url: `${api_name}/${id}`,
      method: 'get'
    })
  },
  // 修改医院设置
  update(hospitalSet) {
    return request({
      url: `${api_name}`,
      method: 'put',
      data: hospitalSet
    })
  }

}
