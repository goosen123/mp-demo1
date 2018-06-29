package com.goosen.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.goosen.commons.annotations.GetMappingNoLog;
import com.goosen.commons.annotations.ResponseResult;
import com.goosen.commons.enums.ResultCode;
import com.goosen.commons.exception.BusinessException;
import com.goosen.commons.model.po.product.Product;
import com.goosen.commons.model.request.product.ProductAddReqData;
import com.goosen.commons.model.request.product.ProductUpdateReqData;
import com.goosen.commons.model.response.BaseCudRespData;
import com.goosen.commons.model.response.product.ProductRespData;
import com.goosen.commons.service.ProductService;
import com.goosen.commons.utils.BeanUtil;
import com.goosen.commons.utils.CommonUtil;
import com.goosen.commons.utils.IdGenUtil;

@Api(value="商品管理",description="商品管理")
@RestController
@RequestMapping(value="product")
public class ProductController extends BaseController{
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ProductService productService;
	
	@ApiOperation(value="添加商品")
	@ResponseResult
	@RequestMapping(value = {"addProduct"},method=RequestMethod.POST)
	public BaseCudRespData<String> addProduct(@Validated @RequestBody ProductAddReqData addReqData) {
		
		if(addReqData == null)
			throw new BusinessException(ResultCode.PARAM_IS_BLANK);
		Product product = new Product();
		product.setId(IdGenUtil.uuid());
		product.setSalesVolume(0);
		BeanUtil.beanCopyNotNull(product, addReqData);
		productService.save(product);
		
		return buildBaseCudRespData(product.getId());
	}
	
	@ApiOperation(value="修改商品")
	@ResponseResult
	@RequestMapping(value = {"updateProduct"},method=RequestMethod.PUT)
	public BaseCudRespData<String> updateProduct(@RequestBody ProductUpdateReqData updateReqData) {
		
		String id = updateReqData.getId();
		Product product = productService.findById(id);
		if(product == null)
			throw new BusinessException(ResultCode.DATA_IS_WRONG);
		BeanUtil.beanCopyNotNull(product, updateReqData);
		productService.update(product);
		
		return buildBaseCudRespData("");
	}
	
	@ApiOperation(value="获取商品详情")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getProductDetail"},method=RequestMethod.GET)
    public ProductRespData getProductDetail(@ApiParam(name="id",value="商品id",required=true)String id) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(id))
			params.put("id", id);
//		CheckUtil.notNull(null, "user", "参数有误");
//		Assert.notNull(null, "str is not null.");
//		int i = 1/0;
//		String str = null;
//		if(str == null)
//			throw new BusinessException(ResultCode.RESULE_DATA_NONE);
		Map<String, Object> map = productService.findOneByParams(params);
		
        return (ProductRespData) buildBaseModelRespData(map, new ProductRespData());
    }
	
	@ApiOperation(value="获取商品列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getProductList"},method=RequestMethod.GET)
    public List<ProductRespData> getProductList(String productTitle) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(productTitle))
			params.put("productTitle", productTitle);
		List<Map<String, Object>> list = productService.findByParams(params);
		
        return (List<ProductRespData>) buildBaseListRespData(list, new ProductRespData(), new ArrayList<Object>());
    }
	
	@ApiOperation(value="分页获取商品列表")
	@GetMappingNoLog
	@ResponseResult
	@RequestMapping(value = {"getProductListByPage"},method=RequestMethod.GET)
    public PageInfo<ProductRespData> getProductListByPage(Integer pageNum,Integer pageSize,String productTitle) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		if(!CommonUtil.isTrimNull(productTitle))
			params.put("productTitle", productTitle);
		addPageParams(pageNum, pageSize, params);
		PageInfo<Map<String, Object>> pageInfo = productService.findByParamsByPage(params);
		
        return (PageInfo<ProductRespData>) buildBasePageRespData(pageInfo, new ProductRespData(), new PageInfo<Object>());
    }
	
}
