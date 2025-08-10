package com.getgoods.ecommercecoupon.service.couponServiceImple;

import com.getgoods.ecommercecoupon.Util.CouponNotFoundException;
import com.getgoods.ecommercecoupon.model.Coupon;
import com.getgoods.ecommercecoupon.repository.CouponRepository;
import com.getgoods.ecommercecoupon.service.CouponService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImple implements CouponService {

    private CouponRepository couponRepository;
    public CouponServiceImple(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public List<Coupon> findAll() {
        return  couponRepository.findAll();
    }

    @Override
    public Coupon findByCouponCode(String  couponCode) {
        Optional<Coupon> coupon = couponRepository.findByCouponCode(couponCode);
        if(coupon.isPresent()){
            return coupon.get();
        }
        else{
            throw new CouponNotFoundException("Coupon with Code "+couponCode+" not there..");
        }
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateById(Long id, Coupon coupon) {
        Optional<Coupon> couponOptional = couponRepository.findById(id);
        if(couponOptional.isPresent()){
            Coupon existingCoupon = couponOptional.get();
            existingCoupon.setCouponCode(coupon.getCouponCode());
            existingCoupon.setName(coupon.getName());
            existingCoupon.setExpDate(coupon.getExpDate());
            return couponRepository.save(existingCoupon);
        }
        else {
            throw new CouponNotFoundException("Coupon with id "+id+" not there..");
        }
    }

    @Override
    public Coupon findById(Long id) {
        Optional<Coupon> couponOptional = couponRepository.findById(id);
        return  couponOptional.orElseThrow(()-> new CouponNotFoundException("Coupon with id "+id+" not there.."));
    }
}
