package com.iheart.ms.repository;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.iheart.ms.model.Advertiser;


@Mapper
public interface AdvertiserRepository {
	
		@Select("SELECT * FROM ADVERTISER")
		public Collection<Advertiser> findAll();

		@Select("SELECT * FROM advertiser WHERE id = #{id}")
		public Advertiser findById(int id);

		@Delete("DELETE FROM advertiser WHERE id = #{id}")
		public int deleteById(int id);

		@Options(useGeneratedKeys=true,keyProperty="id",  keyColumn="id")
		@Insert("INSERT INTO advertiser(contact_name, credit_limit) VALUES (#{contactName}, #{creditLimit})")
		//@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=int.class)
		public void insert(Advertiser advertiser);

		@Update("Update advertiser set contact_name=#{contactName}, credit_limit=#{creditLimit} where id=#{id}")
		public int update(Advertiser advertiser);


}
