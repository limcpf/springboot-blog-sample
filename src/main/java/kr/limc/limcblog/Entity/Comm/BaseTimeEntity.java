package kr.limc.limcblog.Entity.Comm;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseTimeEntity {
    @Column
    private String createdDate;
    @Column
    private String modifiedDate;

    public BaseTimeEntity(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    protected void setModifiedDate(String modifiedDate) {
        if(!StringUtils.hasLength(modifiedDate)) throw new IllegalArgumentException("수정된 시작은 공백일 수 없습니다.");
        this.modifiedDate = modifiedDate;
    }
}
