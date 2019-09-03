import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etf-underlying-my-suffix.reducer';
import { IEtfUnderlyingMySuffix } from 'app/shared/model/etf-underlying-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfUnderlyingMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EtfUnderlyingMySuffixDetail extends React.Component<IEtfUnderlyingMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { etfUnderlyingEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EtfUnderlying [<b>{etfUnderlyingEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="underlyingId">Underlying Id</span>
            </dt>
            <dd>{etfUnderlyingEntity.underlyingId}</dd>
            <dt>
              <span id="effectiveDate">Effective Date</span>
            </dt>
            <dd>
              <TextFormat value={etfUnderlyingEntity.effectiveDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="productId">Product Id</span>
            </dt>
            <dd>{etfUnderlyingEntity.productId}</dd>
          </dl>
          <Button tag={Link} to="/entity/etf-underlying-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/etf-underlying-my-suffix/${etfUnderlyingEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ etfUnderlying }: IRootState) => ({
  etfUnderlyingEntity: etfUnderlying.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfUnderlyingMySuffixDetail);
