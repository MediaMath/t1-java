package com.mediamath.terminalone.models.helper;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.Exceptions.T1Exception;
import com.mediamath.terminalone.Exceptions.ValidationException;
import com.mediamath.terminalone.models.Pixel;
import com.mediamath.terminalone.models.Pixel.pixel_types;
import com.mediamath.terminalone.models.Pixel.pricing;
import com.mediamath.terminalone.utils.Utility;

public class PixelHelper {

	
	public static void validateRequiredFields(Pixel entity) throws T1Exception {
		if(entity.getPricing().equals(pricing.CPM) && entity.getCost_cpm()<=0f && entity.getCost_pct_cpm()<=0f){
			throw new ValidationException("please enter values for either cost_cpm or cost_pct_cpm");
		}
		
		if(entity.getCurrency()==null && entity.getCurrency_fixed()==null){
			throw new ValidationException("Either currency or currency_fixed must be set.");
		}
		if((entity.getCurrency()!=null || entity.getCurrency_fixed()!=null) && entity.getRevenue()==null){
			throw new ValidationException("Please set value for revenue parameters.");
		}
		if(entity.getCurrency()!=null && entity.getPricing()!=null &&  entity.getCurrency().equals(entity.getRevenue())){
			throw new ValidationException("currency and revenue parameters must be different.");
		}
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the Pixel");
		} else if (entity.getName().length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}
		
		if(entity.getPixel_type() == null){
			throw new ValidationException("please Add Pixel Type");
		}
		
		if(entity.getTag_type() == null){
			throw new ValidationException("please Add Tag Type");
		}
		
		if(entity.getPixel_type().equals(pixel_types.data)){
			if(entity.getAgency_id()<=0){
				throw new ValidationException("please Add Agency");
			}
			if(entity.getProvider_id()<=0){
				throw new ValidationException("please Add Provider");
			}
		}
		
		if(entity.getPixel_type().equals(pixel_types.event)){
			if(entity.getAdvertiser_id()<=0){
				throw new ValidationException("please Add Advertiser");
			}
		}
		
		if(entity.getPixel_type().equals(pixel_types.event) && entity.getKeywords()!=null && entity.getKeywords().length()>64){
			throw new ValidationException("Max Length for Keywords is 64 chars");
		}

	}
	
	public static Form getForm(Pixel entity) {
		
		Form pixelForm = new Form();

		if(entity.getAdvertiser_id()> 0){
			pixelForm.param("advertiser_id", String.valueOf(entity.getAdvertiser_id()));
		}
		if(entity.getAgency_id()> 0){
			pixelForm.param("agency_id", String.valueOf(entity.getAgency_id()));
		}
		if(entity.getProvider_id()> 0){
			pixelForm.param("provider_id", String.valueOf(entity.getProvider_id()));
		}
		if(entity.getCost_cpm()>0f){
			pixelForm.param("cost_cpm", String.valueOf(entity.getCost_cpm()));
		}
		if(entity.getCost_pct_cpm()>0f){
			pixelForm.param("cost_pct_cpm", String.valueOf(entity.getCost_pct_cpm()));
		}
		if(entity.getCost_cpts()>0f){
			pixelForm.param("cost_cpts", String.valueOf(entity.getCost_cpts()));
		}
		if(entity.getCreated_on()!=null){
			pixelForm.param("created_on", entity.getCreated_on().toString());
		}
		if(entity.getCurrency()!=null){
			pixelForm.param("currency", entity.getCurrency().toString());
		}
		if(entity.getCurrency_fixed()!=null){
			pixelForm.param("currency_fixed", entity.getCurrency_fixed());
		}
		
		if(entity.getRevenue()!=null){
			pixelForm.param("revenue", entity.getRevenue().toString());
		}
		pixelForm.param("eligible",Utility.getOnOrOff(entity.isEligible()));
		if(entity.getExternal_identifier()!=null){
			pixelForm.param("external_identifier", entity.getExternal_identifier());
		}
		if(entity.getKeywords()!=null){
			pixelForm.param("keywords", entity.getKeywords());
		}

		if(entity.getName()!=null){
			pixelForm.param("name", entity.getName());
		}

		if(entity.getPixel_type()!=null){
			pixelForm.param("pixel_type", entity.getPixel_type().toString());
		}

		if(entity.getPricing()!=null){
			pixelForm.param("pricing", entity.getPricing().toString());
		}
		if(entity.getRmx_conversion_minutes()>0){
			pixelForm.param("rmx_conversion_minutes", String.valueOf(entity.getRmx_conversion_minutes()));
		}

		if(entity.getRmx_conversion_type()!=null){
			pixelForm.param("rmx_conversion_type", entity.getRmx_conversion_type().toString());
		}

		pixelForm.param("rmx_friendly", Utility.getOnOrOff(entity.isRmx_friendly()));
		pixelForm.param("rmx_merit", Utility.getOnOrOff(entity.isRmx_merit()));

		if(entity.getRmx_pc_window_minutes()>0){
			pixelForm.param("rmx_pc_window_minutes", String.valueOf(entity.getRmx_pc_window_minutes()));
		}

		if(entity.getRmx_pv_window_minutes()>0){
			pixelForm.param("rmx_pv_window_minutes", String.valueOf(entity.getRmx_pv_window_minutes()));
		}

		if(entity.getSegment_op()!=null){
			pixelForm.param("segment_op", entity.getSegment_op());
		}
		pixelForm.param("status", Utility.getOnOrOff(entity.isStatus()));

		if(entity.getTag_type()!=null){
			pixelForm.param("tag_type", entity.getTag_type().toString());
		}
		if(entity.getTags()!=null){
			pixelForm.param("tags", entity.getTags());
		}
		if(entity.getType()!=null){
			pixelForm.param("type", entity.getType());
		}
		if(entity.getVersion()>0){
			pixelForm.param("version", String.valueOf(entity.getVersion()));
		}
		if(entity.getUpdated_on()!=null){
			pixelForm.param("updated_on", entity.getUpdated_on().toString());
		}
		
		
		return pixelForm;
	}
}
