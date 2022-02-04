<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.hosname" placeholder="医院名称" />
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchObj.hoscode" placeholder="医院编号" />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
    </el-form>

    <!-- 工具条 -->
    <div>
      <el-button type="danger" icon="el-icon-delete" size="normal" @click="batchRemove()">批量删除</el-button>
      <el-button type="primary" icon="el-icon-circle-plus-outline" align="right" size="normal" @click="handleAdd()">新增</el-button>
    </div>
    <!-- 医院列表 -->
    <el-table
      :data="list"
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="30" />
      <el-table-column type="index" width="35" />
      <el-table-column prop="hosname" label="医院名称" />
      <el-table-column prop="hoscode" label="医院编号" />
      <el-table-column prop="apiUrl" label="api基础路径" width="200" />
      <el-table-column prop="contactsName" label="联系人姓名" />
      <el-table-column prop="contactsPhone" label="联系人手机" />
      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 0 ? '可用' : '不可用' }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="handleRemove(scope.row.id)">删除</el-button>
          <el-button
            v-if="scope.row.status === 0"
            type="primary"
            size="mini"
            icon="el-icon-unlock"
            @click="handleLock(scope.row.id, 1)"
          >锁定
          </el-button>
          <el-button
            v-if="scope.row.status === 1"
            type="info"
            size="mini"
            icon="el-icon-lock"
            @click="handleLock(scope.row.id, 0)"
          >解锁
          </el-button>
          <router-link :to="'/hosp/hospitalSet/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="current"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />

  </div>
</template>

<script>
import hospitalSet from '@/api/hospital/hospitalSet'

export default {
  // 定义数据模型
  data() {
    return {
      list: [], // 每页数据列表
      current: 1, // 页数
      limit: 3, // 每页记录数
      total: 0, // 总记录数
      multipleSelection: [], // 批量选择中选择的记录列表
      searchObj: {} // 搜索条件
    }
  },

  // 页面渲染成功后获取数据
  created() {
    this.fetchData()
  },

  methods: {
    // 加载列表数据
    fetchData(page = 1) {
      console.log('当前页：', page)
      // 异步获取远程数据（ajax）
      this.current = page
      hospitalSet.getPageList(this.current, this.limit, this.searchObj)
        .then(response => {
          this.list = response.data.records
          this.total = response.data.total
        }).catch(error => {
          console.log(error)
        })
    },
    // 删除操作
    handleRemove(id) {
      this.$confirm('此操作将永久删除医院设置信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // 确定执行then方法
        // 调用接口
        hospitalSet.delete(id).then(() => {
          // 提示
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          // 刷新页面
          this.fetchData(1)
        })
      })
    },
    // 当表格复选框选项发生变化的时候触发
    handleSelectionChange(selection) {
      this.multipleSelection = selection
    },
    // 批量删除
    batchRemove() {
      this.$confirm('此操作将永久删除医院设置信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // 确定执行then方法
        const idList = []
        // 遍历数组得到每个id值，设置到idList里面
        for (let i = 0; i < this.multipleSelection.length; i++) {
          const obj = this.multipleSelection[i]
          const id = obj.id
          idList.push(id)
        }
        // 调用接口
        hospitalSet.batchDelete(idList).then(() => {
          // 提示
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          // 刷新页面
          this.fetchData(1)
        })
      })
    },

    // 锁定/解锁
    handleLock(id, status) {
      hospitalSet.lock(id, status)
        .then(() => {
          // 刷新
          this.fetchData(this.current)
        })
    },
    handleAdd() {
      // 跳转新增页面，使用路由跳转方式实现
      this.$router.push({ path: '/hosp/hospitalSet/add' })
    }
  }
}
</script>

<style scoped>

</style>
