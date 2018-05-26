/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author esraa
 */
@Entity
@Table(name = "user_challenge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserChallenge.findAll", query = "SELECT u FROM UserChallenge u")
    , @NamedQuery(name = "UserChallenge.findByUserId", query = "SELECT u FROM UserChallenge u WHERE u.userChallengePK.userId = :userId")
    , @NamedQuery(name = "UserChallenge.findByChallengeId", query = "SELECT u FROM UserChallenge u WHERE u.userChallengePK.challengeId = :challengeId")
    , @NamedQuery(name = "UserChallenge.findByStartDate", query = "SELECT u FROM UserChallenge u WHERE u.startDate = :startDate")
    , @NamedQuery(name = "UserChallenge.findByEndDate", query = "SELECT u FROM UserChallenge u WHERE u.endDate = :endDate")
    , @NamedQuery(name = "UserChallenge.findByEnded", query = "SELECT u FROM UserChallenge u WHERE u.ended = :ended")})
public class UserChallenge implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserChallengePK userChallengePK;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Size(max = 45)
    @Column(name = "ended")
    private String ended;
    @JoinColumn(name = "challenge_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Challenge challenge;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserChallenge() {
    }

    public UserChallenge(UserChallengePK userChallengePK) {
        this.userChallengePK = userChallengePK;
    }

    public UserChallenge(int userId, int challengeId) {
        this.userChallengePK = new UserChallengePK(userId, challengeId);
    }

    public UserChallengePK getUserChallengePK() {
        return userChallengePK;
    }

    public void setUserChallengePK(UserChallengePK userChallengePK) {
        this.userChallengePK = userChallengePK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userChallengePK != null ? userChallengePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserChallenge)) {
            return false;
        }
        UserChallenge other = (UserChallenge) object;
        if ((this.userChallengePK == null && other.userChallengePK != null) || (this.userChallengePK != null && !this.userChallengePK.equals(other.userChallengePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.UserChallenge[ userChallengePK=" + userChallengePK + " ]";
    }
    
}
