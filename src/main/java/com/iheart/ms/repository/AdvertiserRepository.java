package com.iheart.ms.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iheart.ms.model.Advertiser;


@Mapper
public interface AdvertiserRepository {
	
		@Select("select * from advertiser")
		public List<Advertiser> findAll();

		@Select("SELECT * FROM advertiser WHERE id = #{id}")
		public Advertiser findById(long id);

		@Delete("DELETE FROM advertiser WHERE id = #{id}")
		public int deleteById(long id);

		@Insert("INSERT INTO advertiser(contact_name, credit_limit) VALUES (#{contact_name}, #{credit_limit})")
		public int insert(Advertiser advertiser);

		@Update("Update advertiser set contact_name=#{contact_name}, contact_name=#{contact_name} where id=#{id}")
		public int update(Advertiser advertiser);


}
